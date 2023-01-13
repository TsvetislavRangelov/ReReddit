package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.*;
import sem3.its.ReReddit.business.services.CreateCommentUseCase;
import sem3.its.ReReddit.domain.CreateCommentRequest;
import sem3.its.ReReddit.domain.CreateCommentResponse;
import sem3.its.ReReddit.persistence.CommentRepository;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.CommentEntity;


import java.time.LocalDate;


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
       if(!userRepository.existsById(request.getAuthorId()) &&
               !postRepository.existsById(request.getPostId())){
          throw new InvalidRequestBodyException();
       }
        var commentEntity = CommentEntity.builder()
                .ups(0)
                .downs(0)
                .author(userRepository.findById(request.getAuthorId())
                        .orElseThrow(ResourceDoesNotExistException::new))
                .post(postRepository.findById(request.getPostId())
                        .orElseThrow(ResourceDoesNotExistException::new))
                .body(request.getBody())
                .createdAt(LocalDate.now())
                .build();
        return commentRepository.save(commentEntity);
    }

}
