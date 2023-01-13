package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.CountLogActivityForDateUseCase;

import sem3.its.ReReddit.domain.CountLogActivityForDateResponse;
import sem3.its.ReReddit.persistence.ActivityLogRepository;


import java.time.LocalDate;


@Service
@AllArgsConstructor
public class CountLogActivityForDateUseCaseImpl implements CountLogActivityForDateUseCase {
    private final ActivityLogRepository activityLogRepository;


    @Override
    public CountLogActivityForDateResponse countLogActivityForDate(String date){
        long count = activityLogRepository.countAllByTimestamp(LocalDate.parse(date));
        return CountLogActivityForDateResponse.builder()
                .count(count)
                .build();
    }
}
