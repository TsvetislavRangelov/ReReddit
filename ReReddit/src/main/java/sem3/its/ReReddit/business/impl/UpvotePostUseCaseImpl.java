package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.exception.UserHasAlreadyVotedException;
import sem3.its.ReReddit.business.services.UpvotePostUseCase;
import sem3.its.ReReddit.domain.UpvotePostRequest;
import sem3.its.ReReddit.domain.UpvotePostResponse;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.VoteRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.persistence.entity.VoteEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpvotePostUseCaseImpl implements UpvotePostUseCase {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private VoteRepository voteRepository;

    @Override
    public void upvote(UpvotePostRequest request) {
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
        postEntity.get().setUps(postEntity.get().getUps() + 1);
        postRepository.save(postEntity.get());
        VoteEntity upvote = VoteEntity.builder()
                .post(postEntity.get())
                .user(userEntity.get())
                .type('+').build();
        voteRepository.save(upvote);
    }
}
