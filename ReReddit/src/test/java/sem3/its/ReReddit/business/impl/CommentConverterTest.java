package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.domain.Comment;
import sem3.its.ReReddit.persistence.entity.CommentEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentConverterTest {

    @Test
    void convertComment_ShouldReturnCommentConverted(){
        CommentEntity commentEntity = CommentEntity.builder().id(1L)
                .ups(3)
                .downs(6)
                .body("whatever")
                .author(null)
                .parent(null)
                .build();

        Comment converted = CommentConverter.convert(commentEntity);

        assertEquals(commentEntity.getId(), converted.getId());
        assertEquals(commentEntity.getAuthor(), converted.getAuthor());
    }
}
