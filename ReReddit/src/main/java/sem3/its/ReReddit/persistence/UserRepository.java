package sem3.its.ReReddit.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
    long countAllByRegisteredAt(LocalDate registeredAt);

}
