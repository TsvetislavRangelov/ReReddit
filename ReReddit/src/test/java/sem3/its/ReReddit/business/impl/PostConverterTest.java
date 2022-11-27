package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.domain.Post;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostConverterTest {
    @Test
    void convertPost_ShouldReturnPostConverted(){
        PostEntity postEntity = PostEntity.builder().id(1L).author(UserEntity.builder().id(4L).build()).ups(0).downs(0).body("whatever").comments(4).build();

        Post converted = PostConverter.convert(postEntity);


        assertEquals(postEntity.getId(),converted.getId());
    }
}
