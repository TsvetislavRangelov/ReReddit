package sem3.its.ReReddit.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.its.ReReddit.persistence.entity.ActivityLogEntity;
import java.util.Date;


public interface ActivityLogRepository extends JpaRepository<ActivityLogEntity, Long> {
    long countAllByTimestamp(Date timestamp);
}
