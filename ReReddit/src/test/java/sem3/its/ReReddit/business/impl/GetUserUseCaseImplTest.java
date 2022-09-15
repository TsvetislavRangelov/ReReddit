package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.GetUsersResponse;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void getUser_ShouldReturnUserConverted(){
        UserEntity userEntity = UserEntity.builder().id(1L).username("user1").posts(Collections.emptyList()).build();

        when(userRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(userEntity));
        Optional<User> actual = getUserUseCase.getUser(1);


        User expected = User.builder().id(1L).username("user1").posts(Collections.emptyList()).build();

        // actual can be null but since this is a test no checks are done to verify it
        assertEquals(expected.getId(), actual.orElse(null).getId());

        verify(userRepositoryMock).findById(1L);
    }
}
