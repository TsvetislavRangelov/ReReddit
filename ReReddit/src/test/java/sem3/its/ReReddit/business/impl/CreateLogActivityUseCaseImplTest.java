package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.CreateLogActivityRequest;
import sem3.its.ReReddit.domain.CreateLogActivityResponse;
import sem3.its.ReReddit.persistence.ActivityLogRepository;
import sem3.its.ReReddit.persistence.entity.ActivityLogEntity;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateLogActivityUseCaseImplTest {
    @Mock
    private ActivityLogRepository activityLogRepository;
    @InjectMocks
    private CreateLogActivityUseCaseImpl createLogActivityUseCase;

    @Test
    void createLogActivity_ShouldReturnIdOfCreatedLog(){
        String profile = "xd";
        boolean success = true;
        CreateLogActivityRequest request = CreateLogActivityRequest.builder().success(success).profile(profile).build();
        ActivityLogEntity log = ActivityLogEntity.builder().success(true).profile("xd").timestamp(LocalDate.now()).build();
        ActivityLogEntity returnedLog = ActivityLogEntity.builder().id(1L).success(true).profile("xd").build();

        when(activityLogRepository.save(log))
                .thenReturn(returnedLog);
        CreateLogActivityResponse expected = CreateLogActivityResponse.builder().id(1L).build();

        CreateLogActivityResponse actual = createLogActivityUseCase.createLogActivity(request);

        Assertions.assertEquals(expected, actual);

        verify(activityLogRepository).save(log);

    }
}
