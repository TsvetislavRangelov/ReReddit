package sem3.its.ReReddit.business.impl;

import sem3.its.ReReddit.domain.ActivityLog;
import sem3.its.ReReddit.persistence.entity.ActivityLogEntity;

public class ActivityLogConverter {
    private ActivityLogConverter () {}

    public static ActivityLog convert(ActivityLogEntity entity) {
        return ActivityLog.builder()
                .id(entity.getId())
                .success(entity.isSuccess())
                .timestamp(entity.getTimestamp())
                .profile(entity.getProfile())
                .build();

    }
}
