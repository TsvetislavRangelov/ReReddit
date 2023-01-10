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
import sem3.its.ReReddit.domain.ValidatePasswordForChangeRequest;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidatePasswordForChangeUseCaseImplTest {
    @Mock
    private PasswordVerifier passwordVerifier;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ValidatePasswordForChangeUseCaseImpl validatePasswordForChangeUseCase;

    @Test
    void validate_ShouldReturnTrue() {
        long userId = 1;
        String oldPassword = "123";
        ValidatePasswordForChangeRequest request = ValidatePasswordForChangeRequest.builder().oldPassword(oldPassword)
                .userId(userId).build();
        UserEntity user = UserEntity.builder().id(userId).password("123").build();
        when(passwordVerifier.verify(user.getPassword(), oldPassword.toCharArray()))
                .thenReturn(true);
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        validatePasswordForChangeUseCase.validate(request);

        verify(passwordVerifier).verify(user.getPassword(), oldPassword.toCharArray());
        verify(userRepository).findById(1L);
    }

    @Test
    void validate_ShouldThrowResourceDoesNotExistException() {
        long userId = 1;
        String oldPassword = "123";
        ValidatePasswordForChangeRequest request = ValidatePasswordForChangeRequest.builder().oldPassword(oldPassword)
                .userId(userId).build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());
        ResourceDoesNotExistException exception = Assertions.assertThrows(ResourceDoesNotExistException.class, () -> {
            validatePasswordForChangeUseCase.validate(request);
        }, "RESOURCE_DOESNT_EXIST");
        Assertions.assertEquals("404 NOT_FOUND \"RESOURCE_DOESNT_EXIST\"", exception.getMessage());
    }

    @Test
    void validate_ShouldReturnInvalidCredentialsException() {
        long userId = 1;
        String oldPassword = "incorrect pass";
        ValidatePasswordForChangeRequest request = ValidatePasswordForChangeRequest.builder().oldPassword(oldPassword)
                .userId(userId).build();
        UserEntity user = UserEntity.builder().id(userId).password("123").build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        when(passwordVerifier.verify(user.getPassword(), oldPassword.toCharArray()))
                .thenReturn(false);

        InvalidCredentialsException exception = Assertions.assertThrows(InvalidCredentialsException.class, () -> {
            validatePasswordForChangeUseCase.validate(request);
        }, "INVALID_CREDENTIALS");
        Assertions.assertEquals("400 BAD_REQUEST \"INVALID_CREDENTIALS\"", exception.getMessage());

    }
}
