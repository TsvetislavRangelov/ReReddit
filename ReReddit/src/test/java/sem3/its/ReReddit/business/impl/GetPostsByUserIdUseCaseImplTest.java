package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.GetPostsByUserIdResponse;
import sem3.its.ReReddit.domain.GetPostsResponse;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPostsByUserIdUseCaseImplTest {
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private GetPostsByUserIdUseCaseImpl getPostsByUserIdUseCase;

    @Test
    void getPostsByUserId_ShouldReturnPostsForUserConverted() {
        UserEntity author =  UserEntity.builder().id(1L).username("user1").build();
        PostEntity post1Entity = PostEntity.builder().id(1L).author(author).build();
        PostEntity post2Entity = PostEntity.builder().id(2L).author(author).build();

        when(postRepository.findAllByAuthorId(1L))
                .thenReturn(List.of(post1Entity, post2Entity));
        GetPostsByUserIdResponse actual = getPostsByUserIdUseCase.getPostsByUserId(1L);

        GetPostsByUserIdResponse expected = GetPostsByUserIdResponse.builder().posts(List.of(PostConverter.convert(post1Entity),
                PostConverter.convert(post2Entity))).build();

        assertEquals(expected, actual);

        verify(postRepository).findAllByAuthorId(1L);
    }
}
