package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import sem3.its.ReReddit.domain.GetPostsResponse;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.domain.Post;

import java.util.List;
import java.util.Map;
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
    void getPosts_ShouldReturnPostsConverted() {
       UserEntity author =  UserEntity.builder().id(1L).username("user1").build();
        PostEntity post1Entity = PostEntity.builder().id(1L).author(author).build();
        PostEntity post2Entity = PostEntity.builder().id(2L).author(author).build();

        when(postRepositoryMock.findAllByOrderByIdDesc())
                .thenReturn(List.of(post1Entity, post2Entity));
        GetPostsResponse actual = getPostsUseCase.getPosts();

        GetPostsResponse expected = GetPostsResponse.builder().posts(List.of(PostConverter.convert(post1Entity),
                PostConverter.convert(post2Entity))).build();

        assertEquals(expected, actual);

        verify(postRepositoryMock).findAllByOrderByIdDesc();
    }
}
