package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.InvalidUserException;

import sem3.its.ReReddit.business.services.CreatePostUseCase;
import sem3.its.ReReddit.business.exception.PostHasNoAuthorException;
import sem3.its.ReReddit.domain.CreatePostRequest;
import sem3.its.ReReddit.domain.CreatePostResponse;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreatePostUseCaseImpl implements CreatePostUseCase {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Override
    public CreatePostResponse createPost(CreatePostRequest request){
        PostEntity createdEntity = saveNewPost(request);

        return CreatePostResponse.builder()
                .id(createdEntity.getId())
                .build();
    }

    private PostEntity saveNewPost(CreatePostRequest request){
        if(!userRepository.existsById(request.getAuthorId())){
            throw new InvalidUserException("INVALID_AUTHOR_ID");
        }
        Optional<UserEntity> userOptional = userRepository.findById(request.getAuthorId());
        if(userOptional.isEmpty()){
            throw new PostHasNoAuthorException();
        }
        PostEntity postEntity = PostEntity.builder()
                .author(userOptional.get())
                .body(request.getBody())
                .header(request.getHeader())
                .createdAt(LocalDate.now())
                .build();
        return postRepository.save(postEntity);
    }
}
