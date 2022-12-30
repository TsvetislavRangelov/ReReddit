package sem3.its.ReReddit.business.impl;

import sem3.its.ReReddit.domain.Post;
import sem3.its.ReReddit.persistence.entity.PostEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostConverter {
    private PostConverter(){

    }

    public static Post convert(PostEntity entity){
        return Post.builder()
                .id(entity.getId())
                .body(entity.getBody())
                .header(entity.getHeader())
                .ups(entity.getUps())
                .downs(entity.getDowns())
                .author(UserConverter.convert(entity.getAuthor()))
                .build();
    }
}
