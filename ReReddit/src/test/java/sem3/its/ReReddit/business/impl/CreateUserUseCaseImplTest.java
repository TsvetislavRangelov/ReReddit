package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.security.PasswordHasher;
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
//        UserEntity user = UserEntity.builder()
//                .username("u1")
//                .password("123")
//                .userRoles(Set.of(UserRoleEntity.builder().role(Role.valueOf("STANDARD")).build())).build();
//
//        when(userRepositoryMock.save(user))
//                .thenReturn(user);
//        when(passwordHasher.hash(user.getPassword()))
//                .thenReturn("123");
//
//        CreateUserResponse actual = createUserUseCase.createUser(CreateUserRequest.builder().username("u1").password("123").build());
//
//
//        CreateUserResponse expected = CreateUserResponse.builder().id(user.getId()).build();
//
//        assertEquals(expected.getId(),actual.getId());
//
//        verify(userRepositoryMock).save(user);
//        verify(passwordHasher).hash(user.getPassword());
    }
}
