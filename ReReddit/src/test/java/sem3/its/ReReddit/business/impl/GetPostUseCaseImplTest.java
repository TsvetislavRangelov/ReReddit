package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.domain.Post;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPostUseCaseImplTest {
    @Mock
    private PostRepository postRepositoryMock;

    @InjectMocks
    private GetPostUseCaseImpl getPostsUseCase;

    @Test
    void getPost_ShouldReturnPostConverted(){
        PostEntity postEntity = PostEntity.builder()
                .id(1L)
                .author(UserEntity.builder()
                        .id(4L)
                        .build())
                .ups(0)
                .downs(0)
                .body("whatever")
                .comments(4)
                .build();
        when(postRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(postEntity));
        Optional<Post> actual = getPostsUseCase.getPost(1);

        Post expected = Post.builder()
                .id(1L)
                .author(User.builder()
                        .id(4L)
                        .build())
                .ups(0)
                .downs(0)
                .body("whatever")
                .comments(4)
                .build();
        assert actual.orElse(null) != null;
        assertEquals(expected.getId(), actual.orElse(null).getId());

        verify(postRepositoryMock).findById(1L);
    }
}
