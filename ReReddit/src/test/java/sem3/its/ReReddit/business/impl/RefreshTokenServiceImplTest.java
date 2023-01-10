package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import sem3.its.ReReddit.business.exception.RefreshTokenException;
import sem3.its.ReReddit.domain.RefreshToken;
import sem3.its.ReReddit.persistence.RefreshTokenRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.RefreshTokenEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
//    @MockitoSettings(strictness = Strictness.LENIENT)
//    void createRefreshToken_ShouldReturnNewToken(){
//        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
//                .expiryDate(Instant.now().plus(2, ChronoUnit.MINUTES)).build();
//        UserEntity userEntity = UserEntity.builder().username("new").id(1L).build();
//
//        RefreshTokenEntity returnRefreshTokenEntity = RefreshTokenEntity.builder()
//                .id(1L)
//                .token("aaaaaabbbbcccc")
//                .user(userEntity)
//                .expiryDate(Instant.now().plus(2, ChronoUnit.MINUTES)).build();
//
//        when(userRepository.findById(1L))
//                .thenReturn(Optional.of(userEntity));
//        when(refreshTokenRepository.save(refreshTokenEntity))
//                .thenReturn(returnRefreshTokenEntity);
//
//        //RefreshToken actual = refreshTokenService.createRefreshToken(1);
//        RefreshToken expected = RefreshToken.builder().token("ab").build();
//
//        assertNotNull(expected.getToken());
//
//        verify(refreshTokenRepository).save(refreshTokenEntity);
//        verify(userRepository).findById(1L);
//    }

    @Test
    void verifyExpiration_ShouldReturnRefreshTokenConverted() {
        UserEntity user = UserEntity.builder().id(1L).build();
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder().token("aaabbb")
                .expiryDate(Instant.now().plus(2, ChronoUnit.MINUTES)).user(user).build();

        RefreshToken res = refreshTokenService.verifyExpiration(refreshToken);

        assertEquals(res.getToken(), refreshToken.getToken());

    }
    @Test
    void verifyExpiration_ShouldThrowRefreshTokenException() {
        UserEntity user = UserEntity.builder().id(1L).build();
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder().token("aaabbb")
                .expiryDate(Instant.now().minus(2, ChronoUnit.MINUTES)).user(user).build();

        RefreshTokenException exception = Assertions.assertThrows(RefreshTokenException.class,
                () -> refreshTokenService.verifyExpiration(refreshToken), "Refresh token has expired. Please make a new login request");
        assertEquals("403 FORBIDDEN \"Failed for [aaabbb]: Refresh token has expired. Please make a new login request\"", exception.getMessage());
    }
}
