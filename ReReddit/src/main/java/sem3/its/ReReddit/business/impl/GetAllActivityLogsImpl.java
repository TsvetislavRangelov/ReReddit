package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.GetAllActivityLogsUseCase;
import sem3.its.ReReddit.persistence.ActivityLogRepository;

@Service
@AllArgsConstructor
public class GetAllActivityLogsImpl implements GetAllActivityLogsUseCase {

    private final ActivityLogRepository activityLogRepository;


    @Override
    public long getAllActivityLogs() {
        return activityLogRepository.count();
    }
}
