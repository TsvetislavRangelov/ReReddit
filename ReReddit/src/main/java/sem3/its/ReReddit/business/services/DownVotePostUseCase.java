package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.DownVotePostRequest;

public interface DownVotePostUseCase {
    void downvote(DownVotePostRequest request);
}
