package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.GetPreviousVoteRequest;
import sem3.its.ReReddit.domain.GetPreviousVoteResponse;
import sem3.its.ReReddit.domain.Vote;

import java.util.Optional;

public interface GetPreviousVoteUseCase {
    GetPreviousVoteResponse getVote(GetPreviousVoteRequest request);
}
