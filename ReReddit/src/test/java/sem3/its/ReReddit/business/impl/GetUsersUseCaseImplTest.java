package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.GetUsersResponse;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetUsersUseCaseImplTest {

    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private GetUsersUseCaseImpl getUsersUseCase;
    @Test
    void getUsers_ShouldReturnAllUsersConverted() {
        UserEntity user1Entity = UserEntity.builder().id(1L).username("user1").posts(Collections.emptyList()).build();
        UserEntity user2Entity = UserEntity.builder().id(2L).username("user2").posts(Collections.emptyList()).build();
        when(userRepositoryMock.findAll())
                .thenReturn(List.of(user1Entity, user2Entity));

        GetUsersResponse actual = getUsersUseCase.getUsers();

        User user1 = User.builder().id(1L).username("user1").posts(Collections.emptyList()).build();
        User user2 = User.builder().id(2L).username("user2").posts(Collections.emptyList()).build();

        GetUsersResponse expected = GetUsersResponse.builder().users(List.of(user1, user2)).build();
        assertEquals(expected, actual);

        verify(userRepositoryMock).findAll();
    }
//    @Test
//    void getUser_ShouldReturnConvertedUser(){
//        UserEntity userEntity = UserEntity.builder().id(1L).username("user1").posts(List.of(PostEntity.builder()
//                .author(null).body("postxd").downs(1).ups(10).header("headerxd").comments(Collections.emptyList()).build())).build();
//        userEntity.getPosts().get(0).setAuthor(userEntity);
//    }
}