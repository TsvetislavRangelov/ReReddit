package sem3.its.ReReddit.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.its.ReReddit.persistence.entity.RefreshTokenEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);

    int deleteByUser(UserEntity user);
}
