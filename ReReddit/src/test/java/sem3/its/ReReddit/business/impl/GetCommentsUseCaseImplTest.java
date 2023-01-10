package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.*;
import sem3.its.ReReddit.persistence.CommentRepository;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.CommentEntity;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class GetCommentsUseCaseImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private GetCommentsUseCaseImpl getCommentsUseCase;

    @Test
    void getComments_ShouldReturnCommentsConverted() {
        UserEntity author = UserEntity.builder().id(1L).password("").username("").email("").build();
        PostEntity post = PostEntity.builder().author(author).comments(2).build();
        CommentEntity comment1Entity = CommentEntity.builder().id(1L).post(post).author(author)
        .build();
        CommentEntity comment2Entity = CommentEntity.builder().id(2L).post(post).author(author)
        .build();

        when(commentRepository.findAllByPostId(1L))
                .thenReturn(List.of(comment1Entity, comment2Entity));
        GetCommentsResponse actual = getCommentsUseCase.getComments(GetCommentsRequest.builder().postId(1L).build());

        GetCommentsResponse expected = GetCommentsResponse.builder().comments(List.of(CommentConverter.convert(comment1Entity),
                CommentConverter.convert(comment2Entity))).build();

        assertEquals(expected.getComments().get(0).getId(), actual.getComments().get(0).getId());

        verify(commentRepository).findAllByPostId(1L);
    }
}
