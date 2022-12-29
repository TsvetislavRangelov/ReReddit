package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.CountLogActivityForDateUseCase;
import sem3.its.ReReddit.domain.CountLogActivityForDateRequest;
import sem3.its.ReReddit.domain.CountLogActivityForDateResponse;
import sem3.its.ReReddit.persistence.ActivityLogRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class CountLogActivityForDateUseCaseImpl implements CountLogActivityForDateUseCase {
    private final ActivityLogRepository activityLogRepository;


    @Override
    public CountLogActivityForDateResponse countLogActivityForDate(CountLogActivityForDateRequest request) {
        long count = activityLogRepository.countAllByTimestamp(request.getTimestamp());
        return CountLogActivityForDateResponse.builder()
                .count(count)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
