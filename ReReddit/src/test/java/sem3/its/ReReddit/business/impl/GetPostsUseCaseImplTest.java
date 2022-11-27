package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.GetPostsResponse;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.domain.Post;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
 class GetPostsUseCaseImplTest {
   @Mock
   private PostRepository postRepositoryMock;
   @InjectMocks
    private GetPostsUseCaseImpl getPostsUseCase;

   @Test
    void getPosts_ShouldReturnPostsConverted(){
       PostEntity post1Entity = PostEntity.builder().id(1L).author(
                UserEntity.builder().id(1L).username("user1").build()
       ).build();
       PostEntity post2Entity = PostEntity.builder().id(2L).author(
               UserEntity.builder().id(2L).username("user2").build()
       ).build();

       when(postRepositoryMock.findAll())
               .thenReturn(List.of(post1Entity, post2Entity));
       GetPostsResponse actual = getPostsUseCase.getPosts();

       Post post1 = Post.builder().id(1L).author(User.builder().id(1L).username("user1").build()).build();
       Post post2 = Post.builder().id(2L).author(User.builder().id(2L).username("user2").build()).build();

       GetPostsResponse expected = GetPostsResponse.builder().posts(List.of(post1, post2)).build();

       assertEquals(expected, actual);

       verify(postRepositoryMock).findAll();


   }
}
