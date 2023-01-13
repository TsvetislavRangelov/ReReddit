package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPasswordForUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private GetPasswordForUserUseCaseImpl getPasswordForUserUseCase;

    @Test
    void getPassword_ShouldReturnPasswordHashedForUser(){
        UserEntity user = UserEntity.builder().id(1L).password("123").build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));
        String expected = "123";
        String actual = getPasswordForUserUseCase.getPassword(1L);

        Assertions.assertEquals(expected, actual);

        verify(userRepository).findById(1L);
    }
    @Test
    void getPassword_ShouldThrowException(){

        ResourceDoesNotExistException exception = Assertions.assertThrows(ResourceDoesNotExistException.class, () -> {
            getPasswordForUserUseCase.getPassword(1);
        }, "RESOURCE_DOESNT_EXIST");
        Assertions.assertEquals("404 NOT_FOUND \"RESOURCE_DOESNT_EXIST\"", exception.getMessage());
    }
}
