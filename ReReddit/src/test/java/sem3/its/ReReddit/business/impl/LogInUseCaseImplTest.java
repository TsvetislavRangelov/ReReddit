package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.exception.InvalidCredentialsException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.security.PasswordVerifier;
import sem3.its.ReReddit.business.services.AccessTokenEncoder;
import sem3.its.ReReddit.business.services.RefreshTokenService;
import sem3.its.ReReddit.domain.AccessToken;
import sem3.its.ReReddit.domain.Enums.Role;
import sem3.its.ReReddit.domain.LoginRequest;
import sem3.its.ReReddit.domain.LoginResponse;
import sem3.its.ReReddit.domain.RefreshToken;
import sem3.its.ReReddit.persistence.RefreshTokenRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.persistence.entity.UserRoleEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogInUseCaseImplTest {
    @Mock
    private AccessTokenEncoder encoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RefreshTokenRepository refreshTokenRepository;
    @Mock
    private PasswordVerifier passwordVerifier;
    @Mock
    private RefreshTokenService refreshTokenService;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void login(){
        UserEntity user = UserEntity.builder().id(1L).email("new@gmail.com")
                .username("new")
                .password("12345").build();
        user.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(user)
                        .role(Role.STANDARD)
                        .build()));
        LoginRequest request = LoginRequest.builder().email("new@gmail.com").password("12345").build();

        when(userRepository.findByEmail("new@gmail.com"))
                .thenReturn(Optional.of(user));
        when(passwordVerifier.verify("12345", "12345".toCharArray()))
                .thenReturn(true);
        when(encoder.encode(AccessToken.builder()
                .userId(1L)
                .roles(List.of("STANDARD"))
                .subject("new").build())).thenReturn("tokenstring");
        when(refreshTokenService.createRefreshToken(1L)).thenReturn(RefreshToken.builder().token("refreshtokenstring")
                .expiryDate(Instant.now().plus(8, ChronoUnit.MINUTES)).build());

        LoginResponse actual = loginUseCase.login(request);
        LoginResponse expected = LoginResponse.builder().accessToken("tokenstring").refreshToken("refreshtokenstring")
                .id(user.getId())
                .roles(List.of("STANDARD"))
                .username("new").build();
        assertEquals(actual, expected);

        verify(userRepository).findByEmail("new@gmail.com");
        verify(passwordVerifier).verify("12345", "12345".toCharArray());
        verify(encoder).encode(AccessToken.builder()
                .userId(1L)
                .roles(List.of("STANDARD"))
                .subject("new").build());
        verify(refreshTokenService).createRefreshToken(1L);
    }

    @Test
    void login_ShouldThrowResourceDoesNotExistException(){
        ResourceDoesNotExistException exception = Assertions.assertThrows(ResourceDoesNotExistException.class, () -> {
           loginUseCase.login(LoginRequest.builder().email("w").password("123").build());
        }, "RESOURCE_DOESNT_EXIST");
        Assertions.assertEquals("404 NOT_FOUND \"RESOURCE_DOESNT_EXIST\"", exception.getMessage());
    }
    @Test
    void login_ShouldThrowNullPointerException() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            loginUseCase.login(LoginRequest.builder().build());
        }, "RESOURCE_DOESNT_EXIST");
        Assertions.assertEquals("Cannot invoke \"String.toCharArray()\" because the return value of \"sem3.its.ReReddit.domain.LoginRequest.getPassword()\" is null", exception.getMessage());
    }
    @Test
    void login_ShouldThrowInvalidCredentialsException() {
        LoginRequest request = LoginRequest.builder().email("w").password("123").build();
        UserEntity user = UserEntity.builder().email("w").password("321").build();
        when(userRepository.findByEmail("w"))
                .thenReturn(Optional.of(user));
        when(passwordVerifier.verify(user.getPassword(), request.getPassword().toCharArray()))
                .thenReturn(false);
        InvalidCredentialsException exception = Assertions.assertThrows(InvalidCredentialsException.class, () -> {
            loginUseCase.login(request);
        }, "INVALID_CREDENTIALS");
        Assertions.assertEquals("400 BAD_REQUEST \"INVALID_CREDENTIALS\"", exception.getMessage());
    }
}
