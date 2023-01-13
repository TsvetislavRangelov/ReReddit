package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.GetPreviousVoteRequest;
import sem3.its.ReReddit.domain.GetPreviousVoteResponse;
import sem3.its.ReReddit.persistence.VoteRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.persistence.entity.VoteEntity;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPreviousVoteUseCaseImplTest {
    @Mock
    private VoteRepository voteRepository;
    @InjectMocks
    private GetPreviousVoteUseCaseImpl getPreviousVoteUseCase;

    @Test
    void getVote_ShouldReturnVoteByUserAndPost(){
        long userId = 1;
        long postId = 1;
        GetPreviousVoteRequest request = GetPreviousVoteRequest.builder().postId(postId).userId(userId).build();
        PostEntity post = PostEntity.builder().id(postId).build();
        UserEntity user = UserEntity.builder().id(userId).build();
        VoteEntity vote = VoteEntity.builder().post(post).user(user).id(1L).type('-').build();

        when(voteRepository.findByUserIdAndPostId(userId, postId))
                .thenReturn(Optional.of(vote));

        GetPreviousVoteResponse expected = GetPreviousVoteResponse.builder().hasVoted(true).type('-').build();

        GetPreviousVoteResponse actual = getPreviousVoteUseCase.getVote(request);
        Assertions.assertEquals(expected, actual);

        verify(voteRepository).findByUserIdAndPostId(userId, postId);
    }
    @Test
    void getVote_ShouldReturnVoteByUserAndPostHasntVoted(){
        long userId = 1;
        long postId = 1;
        GetPreviousVoteRequest request = GetPreviousVoteRequest.builder().postId(postId).userId(userId).build();

        when(voteRepository.findByUserIdAndPostId(userId, postId))
                .thenReturn(Optional.empty());

        GetPreviousVoteResponse expected = GetPreviousVoteResponse.builder().hasVoted(false).build();

        GetPreviousVoteResponse actual = getPreviousVoteUseCase.getVote(request);
        Assertions.assertEquals(expected, actual);

        verify(voteRepository).findByUserIdAndPostId(userId, postId);
    }
}
