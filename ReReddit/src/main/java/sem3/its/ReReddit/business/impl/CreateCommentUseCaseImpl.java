package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.CommentCreationException;
import sem3.its.ReReddit.business.exception.InvalidCredentialsException;
import sem3.its.ReReddit.business.exception.InvalidRequestBodyException;
import sem3.its.ReReddit.business.services.CreateCommentUseCase;
import sem3.its.ReReddit.domain.CreateCommentRequest;
import sem3.its.ReReddit.domain.CreateCommentResponse;
import sem3.its.ReReddit.persistence.CommentRepository;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.CommentEntity;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateCommentUseCaseImpl implements CreateCommentUseCase {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CreateCommentResponse createComment(CreateCommentRequest request){
        CommentEntity created = saveNewComment(request);

        if(created != null){
            return CreateCommentResponse.builder()
                    .id(created.getId())
                    .build();
        }
        throw new CommentCreationException("SERVER_ERROR");
    }

    private CommentEntity saveNewComment(CreateCommentRequest request) throws InvalidRequestBodyException{
        Optional<UserEntity> author = userRepository.findById(request.getAuthor_id());

        Optional<PostEntity> post = postRepository.findById(request.getPost_id());


        return author.isPresent() && post.isPresent() ? commentRepository
                .save(
                        CommentEntity.builder().body(request.getBody())
                                .downs(0)
                                .ups(0)
                                .author(author.get())
                                .post(post.get())
                                .parent(null)
                                .build()
                ) : null;
    }

}
