package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.services.GetCommentsUseCase;
import sem3.its.ReReddit.domain.Comment;
import sem3.its.ReReddit.domain.GetCommentsRequest;
import sem3.its.ReReddit.domain.GetCommentsResponse;
import sem3.its.ReReddit.persistence.CommentRepository;
import sem3.its.ReReddit.persistence.entity.CommentEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetCommentsUseCaseImpl implements GetCommentsUseCase {
    private CommentRepository commentRepository;

    @Override
    public GetCommentsResponse getComments(final GetCommentsRequest request){
        List<CommentEntity> results;
        if(request.getPostId() != 0){
            results = commentRepository.findAllByPostId(request.getPostId());
        }
        else{
            throw new ResourceDoesNotExistException();
        }

        final var res = new GetCommentsResponse();
        List<Comment> comments = results.stream()
                .map(CommentConverter::convert)
                .collect(Collectors.toList());
        res.setComments(comments);
        return res;
    }
}
