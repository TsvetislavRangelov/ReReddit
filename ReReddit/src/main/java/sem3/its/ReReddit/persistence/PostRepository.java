package sem3.its.ReReddit.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
