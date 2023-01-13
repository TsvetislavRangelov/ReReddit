package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.exception.InvalidRequestBodyException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.exception.UsernameAlreadyExistsException;
import sem3.its.ReReddit.business.security.PasswordHasher;
import sem3.its.ReReddit.domain.CreateCommentRequest;
import sem3.its.ReReddit.domain.CreateUserRequest;
import sem3.its.ReReddit.domain.CreateUserResponse;
import sem3.its.ReReddit.domain.Enums.Role;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.persistence.entity.UserRoleEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class CreateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PasswordHasher passwordHasher;
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void createUser_ShouldReturnIdOfCreatedUser(){
        UserEntity user = UserEntity.builder()
                .username("u1")
                .password("123")
                .registeredAt(LocalDate.now())
                .userRoles(Set.of(UserRoleEntity.builder().role(Role.valueOf("STANDARD")).build())).build();
        UserEntity generatedUser = UserEntity.builder()
                .id(1L)
                .username("u1")
                .password("123")
                .registeredAt(LocalDate.now())
                .userRoles(Set.of(UserRoleEntity.builder().role(Role.valueOf("STANDARD")).build())).build();

        when(userRepositoryMock.save(user))
                .thenReturn(generatedUser);
        when(passwordHasher.hash(user.getPassword()))
                .thenReturn("123");

        CreateUserResponse actual = createUserUseCase.createUser(CreateUserRequest.builder().username("u1").password("123").build());


        CreateUserResponse expected = CreateUserResponse.builder().id(1L).build();

        assertEquals(expected.getId(),actual.getId());

        verify(userRepositoryMock).save(user);
        verify(passwordHasher).hash(user.getPassword());
    }

    @Test
    void createUser_ShouldThrowUsernameAlreadyExistsException() {
        CreateUserRequest request = CreateUserRequest.builder().username("xd").build();
        when(userRepositoryMock.existsByUsername("xd"))
                .thenReturn(true);
        UsernameAlreadyExistsException exception = Assertions.assertThrows(UsernameAlreadyExistsException.class, () -> {
           createUserUseCase.createUser(request);
        }, "RESOURCE_DOESNT_EXIST");
        Assertions.assertEquals("409 CONFLICT \"USERNAME_ALREADY_EXISTS\"", exception.getMessage());
    }

    @Test
    void createUser_ShouldReturnInvalidRequestBodyException(){
        CreateUserRequest request = CreateUserRequest.builder().username("xd").password("").build();
        InvalidRequestBodyException exception = Assertions.assertThrows(InvalidRequestBodyException.class, () -> {
            createUserUseCase.createUser(request);
        }, "INVALID_REQUEST_BODY");
        Assertions.assertEquals("400 BAD_REQUEST \"INVALID_REQUEST_BODY\"", exception.getMessage());
    }
}
