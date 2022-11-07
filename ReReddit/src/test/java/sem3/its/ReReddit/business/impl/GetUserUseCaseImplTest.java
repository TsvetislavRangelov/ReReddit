package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class GetUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void getUser_ShouldReturnUserConverted(){
        UserEntity userEntity = UserEntity.builder().id(1L).username("user1").build();

        when(userRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(userEntity));
        Optional<User> actual = getUserUseCase.getUser(1);


        User expected = User.builder().id(1L).username("user1").build();

        assert actual.orElse(null) != null;
        assertEquals(expected.getId(), actual.orElse(null).getId());

        verify(userRepositoryMock).findById(1L);
    }
}
