package sem3.its.ReReddit.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.its.ReReddit.persistence.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, String> {
}
