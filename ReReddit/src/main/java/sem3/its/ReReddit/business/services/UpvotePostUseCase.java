package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.UpvotePostRequest;

public interface UpvotePostUseCase {
    void upvote(UpvotePostRequest request);
}
