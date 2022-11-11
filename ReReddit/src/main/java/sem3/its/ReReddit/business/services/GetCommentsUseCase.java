package sem3.its.ReReddit.business.services;
import sem3.its.ReReddit.domain.GetCommentsRequest;
import sem3.its.ReReddit.domain.GetCommentsResponse;

public interface GetCommentsUseCase {
    GetCommentsResponse getComments(GetCommentsRequest request);
}
