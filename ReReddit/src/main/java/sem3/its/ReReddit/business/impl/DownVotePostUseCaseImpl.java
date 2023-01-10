package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.exception.UserHasAlreadyVotedException;
import sem3.its.ReReddit.business.services.DownVotePostUseCase;
import sem3.its.ReReddit.domain.DownVotePostRequest;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.VoteRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.persistence.entity.VoteEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DownVotePostUseCaseImpl implements DownVotePostUseCase {
    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    @Override
    public void downvote(DownVotePostRequest request) {
        Optional<VoteEntity> previousVote = voteRepository.findByUserIdAndPostId(
                request.getUserId(), request.getPostId()
        );
        if(previousVote.isPresent()){
            throw new UserHasAlreadyVotedException();
        }
        Optional<UserEntity> userEntity = userRepository.findById(request.getUserId());

        if(userEntity.isEmpty()){
            throw new ResourceDoesNotExistException();
        }
        Optional<PostEntity> postEntity = postRepository.findById(request.getPostId());
        if(postEntity.isEmpty()){
            throw new ResourceDoesNotExistException();
        }
        postEntity.get().setDowns(postEntity.get().getDowns() + 1);
        postRepository.save(postEntity.get());
        VoteEntity downvote = VoteEntity.builder()
                .post(postEntity.get())
                .user(userEntity.get())
                .type('-').build();
        voteRepository.save(downvote);
    }
}
