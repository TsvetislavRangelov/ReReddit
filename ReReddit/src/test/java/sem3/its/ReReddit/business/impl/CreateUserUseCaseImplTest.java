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

import java.util.Optional;

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
        UserEntity user = UserEntity.builder().username("u1").password("123").role(Role.STANDARD).build();

        when(userRepositoryMock.save(Mockito.any(UserEntity.class)))
                .thenReturn(user);

        CreateUserResponse actual = createUserUseCase.createUser(CreateUserRequest.builder().username("u1").password("123").build());


        CreateUserResponse expected = CreateUserResponse.builder().id(user.getId()).build();

        assertEquals(expected.getId(),actual.getId());

        verify(userRepositoryMock).save(Mockito.any(UserEntity.class));
    }
}
