package sem3.its.ReReddit.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.its.ReReddit.persistence.entity.AnnouncementEntity;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, String> {
}
