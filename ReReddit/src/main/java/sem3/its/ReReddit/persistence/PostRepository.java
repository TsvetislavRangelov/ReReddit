package sem3.its.ReReddit.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findAllByAuthorId(long authorId);
    List<PostEntity> findAllByOrderByIdDesc();
    long countAllByCreatedAt(LocalDate createdAt);
}
