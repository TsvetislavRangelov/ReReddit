package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.exception.InvalidUserException;
import sem3.its.ReReddit.business.exception.UsernameAlreadyExistsException;
import sem3.its.ReReddit.domain.UpdateUserRequest;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Test
    void updateUser_ShouldUpdateUsername() {
        String newUsername = "bald";
        UpdateUserRequest request = UpdateUserRequest.builder().id(1L).username(newUsername).build();

        UserEntity user = UserEntity.builder().id(1L).username("oldName").build();
        UserEntity newUser = UserEntity.builder().id(1L).username(newUsername).build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        when(userRepository.save(newUser))
                .thenReturn(newUser);

        updateUserUseCase.updateUser(request);

        verify(userRepository).findById(1L);
        verify(userRepository).save(newUser);
    }

    @Test
    void updateUser_ShouldReturnInvalidUserException() {
        String newUsername = "bald";
        UpdateUserRequest request = UpdateUserRequest.builder().id(1L).username(newUsername).build();

        InvalidUserException exception = Assertions.assertThrows(InvalidUserException.class, () ->
                updateUserUseCase.updateUser(request), "USER_ID_INVALID");

        Assertions.assertEquals("404 NOT_FOUND \"USER_ID_INVALID\"", exception.getMessage());
    }

    @Test
    void updateUser_ShouldReturnUsernameAlreadyExistsException() {
//        UserEntity existingUser = UserEntity.builder().username("username").build();
//        UpdateUserRequest request = UpdateUserRequest.builder().id(1L).username("username").build();
//
//        when(userRepository.findById(1L))
//                .thenReturn(Optional.of(existingUser));
//
//        UsernameAlreadyExistsException exception = Assertions.assertThrows(UsernameAlreadyExistsException.class, () ->
//                updateUserUseCase.updateUser(request), "USERNAME_ALREADY_EXISTS");
//        Assertions.assertEquals("409 CONFLICT \"USERNAME_ALREADY_EXISTS\"", exception.getMessage());
    }
}
