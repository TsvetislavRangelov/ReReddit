package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.GetTotalUserCountUseCase;
import sem3.its.ReReddit.persistence.UserRepository;

@Service
@AllArgsConstructor
public class GetTotalUserCountUseCaseImpl implements GetTotalUserCountUseCase {
    private final UserRepository userRepository;


    @Override
    public long getTotalUserCount() {
        return userRepository.count();
    }
}
