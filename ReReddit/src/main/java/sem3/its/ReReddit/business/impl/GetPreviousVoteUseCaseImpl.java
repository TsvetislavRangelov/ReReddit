package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.GetPreviousVoteUseCase;
import sem3.its.ReReddit.domain.GetPreviousVoteRequest;
import sem3.its.ReReddit.domain.GetPreviousVoteResponse;
import sem3.its.ReReddit.domain.Vote;
import sem3.its.ReReddit.persistence.VoteRepository;
import sem3.its.ReReddit.persistence.entity.VoteEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetPreviousVoteUseCaseImpl implements GetPreviousVoteUseCase {
    private VoteRepository voteRepository;


    @Override
    public GetPreviousVoteResponse getVote(GetPreviousVoteRequest request) {
        Optional<VoteEntity> vote = voteRepository.findByUserIdAndPostId(request.getUserId(), request.getPostId());
        if(vote.isPresent()){
            return GetPreviousVoteResponse.builder().hasVoted(true).type(vote.get().getType()).build();
        }
        return GetPreviousVoteResponse.builder().hasVoted(false).build();
    }
}
