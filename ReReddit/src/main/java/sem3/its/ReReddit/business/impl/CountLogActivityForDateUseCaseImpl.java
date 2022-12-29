package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.CountLogActivityForDateUseCase;
import sem3.its.ReReddit.domain.CountLogActivityForDateRequest;
import sem3.its.ReReddit.domain.CountLogActivityForDateResponse;
import sem3.its.ReReddit.persistence.ActivityLogRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Service
@AllArgsConstructor
public class CountLogActivityForDateUseCaseImpl implements CountLogActivityForDateUseCase {
    private final ActivityLogRepository activityLogRepository;


    @Override
    public CountLogActivityForDateResponse countLogActivityForDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date actualDate = formatter.parse(date);
        long count = activityLogRepository.countAllByTimestamp(actualDate);
        return CountLogActivityForDateResponse.builder()
                .count(count)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
