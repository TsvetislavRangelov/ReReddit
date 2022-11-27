package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.RefreshToken;
import sem3.its.ReReddit.persistence.entity.RefreshTokenEntity;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(long userId);
    Optional<RefreshTokenEntity> findByToken(String token);
    RefreshToken verifyExpiration(RefreshTokenEntity token);
}
