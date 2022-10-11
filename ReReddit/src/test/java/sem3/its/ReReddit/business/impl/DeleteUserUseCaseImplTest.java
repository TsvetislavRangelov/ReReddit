package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void deleteUser_ShouldDeleteUserFromRepo(){
        UserEntity user = UserEntity.builder().id(2L).username("u2").build();

        userRepositoryMock.save(user);

        userRepositoryMock.deleteById(2L);

        assertEquals(Optional.empty(), userRepositoryMock.findById(2L));

    }
}
