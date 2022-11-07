package sem3.its.ReReddit.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.its.ReReddit.persistence.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {}
