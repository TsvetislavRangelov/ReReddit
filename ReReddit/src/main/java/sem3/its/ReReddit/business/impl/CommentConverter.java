package sem3.its.ReReddit.business.impl;

import org.springframework.beans.BeanUtils;
import sem3.its.ReReddit.domain.Comment;
import sem3.its.ReReddit.persistence.entity.CommentEntity;

public class CommentConverter {
    private CommentConverter() {

    }

    public static Comment convert(CommentEntity entity){
        return Comment.builder()
                .createdAt(entity.getCreatedAt())
                .id(entity.getId())
                .ups(entity.getUps())
                .downs(entity.getDowns())
                .author(UserConverter.convert(entity.getAuthor()))
                .body(entity.getBody())
                .build();
    }
}
