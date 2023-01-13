package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.CountLogActivityForDateResponse;
import sem3.its.ReReddit.persistence.ActivityLogRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountLogActivityForDateUseCaseImplTest {
    @Mock
    private ActivityLogRepository activityLogRepository;
    @InjectMocks
    private CountLogActivityForDateUseCaseImpl countLogActivityForDateUseCase;

    @Test
    void countLogActivityForDate_ShouldReturnCountOfLogs(){
        String date = "2022-12-28";


        when(activityLogRepository.countAllByTimestamp(LocalDate.parse(date)))
                .thenReturn(3L);
        CountLogActivityForDateResponse expected = CountLogActivityForDateResponse.builder()
                .count(3L).build();
        CountLogActivityForDateResponse actual = countLogActivityForDateUseCase.countLogActivityForDate(date);

        Assertions.assertEquals(expected, actual);
        verify(activityLogRepository).countAllByTimestamp(LocalDate.parse(date));
    }
}
