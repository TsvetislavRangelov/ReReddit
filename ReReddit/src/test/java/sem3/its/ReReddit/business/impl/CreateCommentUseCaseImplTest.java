package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.exception.InvalidRequestBodyException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.services.CreateCommentUseCase;
import sem3.its.ReReddit.domain.*;
import sem3.its.ReReddit.persistence.CommentRepository;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.CommentEntity;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCommentUseCaseImplTest {

    @Mock
    private CommentRepository commentRepositoryMock;
    @Mock
    private PostRepository postRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private CreateCommentUseCaseImpl createCommentUseCase;

    @Test
    void createComment_ShouldReturnIdOfCreatedComment() {
        UserEntity author = UserEntity.builder().id(3L).build();
        PostEntity post = PostEntity.builder().author(author).id(2L).build();
        CommentEntity commentEntity = CommentEntity.builder().author(author)
                .createdAt(LocalDate.now())
                .body("whatever")
                .post(post).build();
        CommentEntity generatedCommentEntity = CommentEntity.builder().author(author)
                .id(1L)
                .createdAt(LocalDate.now())
                .body("whatever")
                .post(post).build();

        when(userRepositoryMock.existsById(3L))
                .thenReturn(true);
        when(userRepositoryMock.findById(3L))
                .thenReturn(Optional.of(author));
        when(postRepositoryMock.findById(2L))
                .thenReturn(Optional.of(post));
        when(commentRepositoryMock.save(commentEntity))
                .thenReturn(generatedCommentEntity);

        CreateCommentResponse actual = createCommentUseCase.createComment(
                CreateCommentRequest.builder()
                        .authorId(author.getId())
                        .postId(post.getId())
                        .body("whatever")
                        .build()
        );

        CreateCommentResponse expected = CreateCommentResponse.builder().id(1L).build();

        assertEquals(expected.getId(), actual.getId());

        verify(userRepositoryMock).existsById(3L);
        verify(userRepositoryMock).findById(3L);
        verify(commentRepositoryMock).save(commentEntity);
    }

    @Test
    void createComment_ShouldThrowInvalidRequestBodyException(){
        InvalidRequestBodyException exception = Assertions.assertThrows(InvalidRequestBodyException.class, () -> {
            createCommentUseCase.createComment(CreateCommentRequest.builder().build());
        }, "INVALID_REQUEST_BODY");
        Assertions.assertEquals("400 BAD_REQUEST \"INVALID_REQUEST_BODY\"", exception.getMessage());
    }
}
