package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.exception.UserHasAlreadyVotedException;
import sem3.its.ReReddit.domain.UpvotePostRequest;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.VoteRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.persistence.entity.VoteEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpvotePostUseCaseImplTest {
    @Mock
    private VoteRepository voteRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UpvotePostUseCaseImpl upvotePostUseCase;

    @Test
    void upvote_ShouldIncreasePostUpsByOne(){
        long userId = 1;
        long postId = 1;
        char type = '+';
        UpvotePostRequest request = UpvotePostRequest.builder().postId(postId).userId(userId).type(type).build();
        UserEntity user = UserEntity.builder().id(1L).build();
        PostEntity post = PostEntity.builder().id(1L).ups(0).downs(0).build();
        PostEntity updatedPost = PostEntity.builder().id(1L).ups(1).downs(0).build();

        when(voteRepository.findByUserIdAndPostId(user.getId(), post.getId()))
                .thenReturn(Optional.empty());
        when(postRepository.findById(1L))
                .thenReturn(Optional.of(post));
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        when(postRepository.save(post))
                .thenReturn(updatedPost);
        upvotePostUseCase.upvote(request);

        verify(voteRepository).findByUserIdAndPostId(user.getId(), post.getId());
        verify(postRepository).findById(1L);
        verify(userRepository).findById(1L);
        verify(postRepository).save(post);
    }
    @Test
    void upvote_ShouldReturnUserHasAlreadyVotedException() {
        long userId = 1;
        long postId = 1;
        char type = '+';
        UpvotePostRequest request = UpvotePostRequest.builder().postId(postId).userId(userId).type(type).build();
        VoteEntity prev = VoteEntity.builder().build();

        when(voteRepository.findByUserIdAndPostId(userId, postId))
                .thenReturn(Optional.of(prev));

        UserHasAlreadyVotedException exception = Assertions.assertThrows(UserHasAlreadyVotedException.class, () -> {
            upvotePostUseCase.upvote(request);
        }, "USER_ALREADY_VOTED");
        assertEquals("409 CONFLICT \"USER_ALREADY_VOTED\"", exception.getMessage());
    }
}
