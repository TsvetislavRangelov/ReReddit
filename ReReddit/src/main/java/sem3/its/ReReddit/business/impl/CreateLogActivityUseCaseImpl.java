package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import sem3.its.ReReddit.business.services.CreateLogActivityUseCase;
import sem3.its.ReReddit.domain.CreateLogActivityRequest;
import sem3.its.ReReddit.domain.CreateLogActivityResponse;
import sem3.its.ReReddit.persistence.ActivityLogRepository;

import sem3.its.ReReddit.persistence.entity.ActivityLogEntity;



import java.time.LocalDate;


@Service
@AllArgsConstructor
public class CreateLogActivityUseCaseImpl implements CreateLogActivityUseCase {
    private ActivityLogRepository activityLogRepository;


    @Override
    public CreateLogActivityResponse createLogActivity(CreateLogActivityRequest request) {
        ActivityLogEntity newLog = saveNewRecord(request);

        return CreateLogActivityResponse.builder()
                .id(newLog.getId())
                .build();

    }

    private ActivityLogEntity saveNewRecord(CreateLogActivityRequest request){
        ActivityLogEntity logEntity = ActivityLogEntity.builder()
                .profile(request.getProfile())
                .timestamp(LocalDate.now())
                .success(request.isSuccess())
                .build();
        return activityLogRepository.save(logEntity);
    }
}
