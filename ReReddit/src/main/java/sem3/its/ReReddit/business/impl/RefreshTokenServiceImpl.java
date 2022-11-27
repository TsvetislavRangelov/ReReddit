package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.RefreshTokenException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.services.RefreshTokenService;
import sem3.its.ReReddit.domain.RefreshToken;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.RefreshTokenRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.RefreshTokenEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private RefreshTokenRepository refreshTokenRepository;
    private UserRepository userRepository;

    @Override
    public RefreshToken createRefreshToken(long userId){
       RefreshTokenEntity token = saveNewToken(userId);

       return RefreshToken.builder()
               .user(UserConverter.convert(token.getUser()))
               .expiryDate(token.getExpiryDate())
               .token(token.getToken())
               .build();
    }

    @Override
    public  Optional<RefreshTokenEntity> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshTokenEntity token) {
        if(token.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(token);
            throw new RefreshTokenException(token.getToken(), "Refresh token was expired. Please make a new login request");
        }
       return RefreshTokenConverter.convert(token);
    }

    private RefreshTokenEntity saveNewToken(long userId){
        Instant now = Instant.now();
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new ResourceDoesNotExistException();
        }
        RefreshTokenEntity token = RefreshTokenEntity.builder()
                .user(userOptional.get())
                .expiryDate(now.plus(8, ChronoUnit.MINUTES))
                .token(UUID.randomUUID().toString())
                .build();
        return refreshTokenRepository.save(token);

    }
}
