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
    public static  List<Post> convertList(List<PostEntity> list){
        if(list == null){
            return Collections.emptyList();
        }
        List<Post> convertedPosts = new ArrayList<>();
        for(PostEntity p : list) {
            convertedPosts.add(Post.builder()
                    .id(p.getId())
                    .author(UserConverter.convert(p.getAuthor()))
                    .body(p.getBody())
                    .ups(p.getUps())
                    .downs(p.getDowns())
                    .header(p.getHeader())
                    .comments(p.getComments())
                    .build());
        }
        return convertedPosts;
    }
}
