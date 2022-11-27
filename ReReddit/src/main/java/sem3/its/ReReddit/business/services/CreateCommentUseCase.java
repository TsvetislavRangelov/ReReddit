package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.CreateCommentRequest;
import sem3.its.ReReddit.domain.CreateCommentResponse;

public interface CreateCommentUseCase {
    CreateCommentResponse createComment(CreateCommentRequest request);
}
