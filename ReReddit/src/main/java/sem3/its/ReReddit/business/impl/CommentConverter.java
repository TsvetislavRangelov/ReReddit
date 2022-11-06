package sem3.its.ReReddit.business.impl;

import org.springframework.beans.BeanUtils;
import sem3.its.ReReddit.domain.Comment;
import sem3.its.ReReddit.persistence.entity.CommentEntity;

public class CommentConverter {
    private CommentConverter() {

    }

    public static Comment convert(CommentEntity entity){
        var converted = new Comment();
        BeanUtils.copyProperties(entity, converted);
        return converted;
    }
}
