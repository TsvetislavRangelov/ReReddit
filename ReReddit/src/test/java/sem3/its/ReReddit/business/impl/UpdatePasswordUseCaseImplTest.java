package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.security.PasswordHasher;
import sem3.its.ReReddit.domain.UpdatePasswordRequest;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdatePasswordUseCaseImplTest {
    @Mock
    private PasswordHasher passwordHasher;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UpdatePasswordUseCaseImpl updatePasswordUseCase;

    @Test
    void updatePassword_ShouldSetNewPasswordHashed() {
        long userId = 1;
        String newPassword = "php lmfao";
        String newHash = "fwkeq";
        UpdatePasswordRequest request = UpdatePasswordRequest.builder().newPassword(newPassword).userId(userId).build();
        UserEntity user = UserEntity.builder().id(1L).build();

        when(passwordHasher.hash(newPassword))
                .thenReturn(newHash);
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        updatePasswordUseCase.updatePassword(request);

        verify(passwordHasher).hash(newPassword);
        verify(userRepository).findById(1L);
    }

    @Test
    void updatePassword_ShouldThrowResourceDoesNotExistException() {
        long userId = 1;
        String newPassword = "php lmfao";
        UpdatePasswordRequest request = UpdatePasswordRequest.builder().newPassword(newPassword).userId(userId).build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());
        ResourceDoesNotExistException exception = Assertions.assertThrows(ResourceDoesNotExistException.class, () -> {
            updatePasswordUseCase.updatePassword(request);
        }, "RESOURCE_DOESNT_EXIST");
        Assertions.assertEquals("404 NOT_FOUND \"RESOURCE_DOESNT_EXIST\"", exception.getMessage());
    }

}
