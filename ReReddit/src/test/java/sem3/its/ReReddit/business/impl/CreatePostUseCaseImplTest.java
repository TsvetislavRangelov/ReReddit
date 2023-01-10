package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.exception.InvalidUserException;
import sem3.its.ReReddit.domain.CreatePostRequest;
import sem3.its.ReReddit.domain.CreatePostResponse;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class CreatePostUseCaseImplTest {
    @Mock
    private PostRepository postRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private CreatePostUseCaseImpl createPostUseCase;

    @Test
    void createPost_ShouldReturnIdOfCreatedPost(){
        UserEntity author = UserEntity.builder().id(3L).build();
        PostEntity postEntity = PostEntity.builder().author(author)
                .body("whatever")
                .header("whatever")
                .createdAt(LocalDate.now())
                .ups(0)
                .downs(0)
                .build();
        PostEntity generatedPostEntity = PostEntity.builder().author(author)
                .id(1L)
                .body("whatever")
                .header("whatever")
                .createdAt(LocalDate.now())
                .ups(0)
                .downs(0)
                .build();

        when(userRepositoryMock.existsById(3L))
                .thenReturn(true);
        when(userRepositoryMock.findById(3L))
                .thenReturn(Optional.of(author));
        when(postRepositoryMock.save(postEntity))
                .thenReturn(generatedPostEntity);

        CreatePostResponse actual = createPostUseCase.createPost(CreatePostRequest.builder().authorId(author.getId())
                .body("whatever")
                .header("whatever").build());

        CreatePostResponse expected = CreatePostResponse.builder().id(1L).build();

        assertEquals(expected, actual);

        verify(userRepositoryMock).existsById(3L);
        verify(userRepositoryMock).findById(3L);
        verify(postRepositoryMock).save(postEntity);
    }

    @Test
    void createPost_InvalidAuthorIdShouldThrowException(){
        CreatePostRequest request = CreatePostRequest.builder().authorId(2).build();

        InvalidUserException exception = Assertions.assertThrows(InvalidUserException.class, () ->
                createPostUseCase.createPost(request), "INVALID_AUTHOR_ID");

        Assertions.assertEquals("404 NOT_FOUND \"INVALID_AUTHOR_ID\"", exception.getMessage());
    }
}
