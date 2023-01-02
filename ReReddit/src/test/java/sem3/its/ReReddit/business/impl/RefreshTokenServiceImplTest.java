package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.persistence.RefreshTokenRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.RefreshTokenEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RefreshTokenServiceImplTest {
    @Mock
    private RefreshTokenRepository refreshTokenRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RefreshTokenServiceImpl refreshTokenService;

//    @Test
//    void createRefreshToken_ShouldReturnTokenString(){
//        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
//                .token("randomstring")
//                .expiryDate(Instant.now().plus(2, ChronoUnit.MINUTES)).build();
//        UserEntity userEntity = UserEntity.builder().username("new").id(1L).build();
//
//        when(userRepository.findById(1L))
//                .thenReturn(Optional.ofNullable(userEntity));
//        when(refreshTokenRepository.save(refreshTokenEntity))
//                .thenReturn(refreshTokenEntity);
//
//        String actual = refreshTokenService.createRefreshToken(1).getToken();
//
//        verify(refreshTokenRepository).save(refreshTokenEntity);
//        verify(userRepository).findById(1L);
   // }
}
