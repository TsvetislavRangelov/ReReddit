package sem3.its.ReReddit.business.impl;

import org.springframework.beans.BeanUtils;
import sem3.its.ReReddit.domain.Comment;
import sem3.its.ReReddit.persistence.entity.CommentEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentConverter {
    private CommentConverter() {

    }

    public static Comment convert(CommentEntity entity){
        Comment converted = new Comment();
        BeanUtils.copyProperties(entity, converted);
        return converted;
    }
}
