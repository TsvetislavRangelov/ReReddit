package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.services.DeleteCommentUseCase;
import sem3.its.ReReddit.persistence.CommentRepository;

@Service
@AllArgsConstructor
public class DeleteCommentUseCaseImpl implements DeleteCommentUseCase {
    private CommentRepository commentRepository;

    @Override
    public void delete(long id) {
        if(!commentRepository.existsById(id)){
            throw new ResourceDoesNotExistException();
        }
        commentRepository.deleteById(id);
    }
}
