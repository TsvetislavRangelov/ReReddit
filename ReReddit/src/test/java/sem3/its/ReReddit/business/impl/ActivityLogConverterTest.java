package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sem3.its.ReReddit.domain.ActivityLog;
import sem3.its.ReReddit.persistence.entity.ActivityLogEntity;

import java.time.LocalDate;

public class ActivityLogConverterTest {

    @Test
    void convert_ShouldReturnActivityLogConverted(){
        ActivityLogEntity entity = ActivityLogEntity.builder().id(1L).profile("whatever")
                .success(false)
                .timestamp(LocalDate.now())
                .build();
        ActivityLog converted = ActivityLogConverter.convert(entity);

        Assertions.assertEquals(entity.getProfile(), converted.getProfile());
        Assertions.assertEquals(entity.getTimestamp(), converted.getTimestamp());
    }
}
