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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateCommentUseCaseImpl implements CreateCommentUseCase {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CreateCommentResponse createComment(CreateCommentRequest request){
        CommentEntity created = saveNewComment(request);

        return CreateCommentResponse.builder()
                .id(created.getId())
                .build();
    }

    private CommentEntity saveNewComment(CreateCommentRequest request) throws InvalidRequestBodyException{
        System.out.println(request.getAuthor());
        System.out.println(request.getPost());
       if(!userRepository.existsById(request.getAuthor().getId()) &&
               !postRepository.existsById(request.getPost().getId())){
          throw new InvalidRequestBodyException();
       }
        CommentEntity commentEntity = CommentEntity.builder()
                .ups(0)
                .downs(0)
                .author(request.getAuthor())
                .post(request.getPost())
                .body(request.getBody())
                .createdAt(LocalDateTime.now())
                .build();
        return commentRepository.save(commentEntity);
    }

}
