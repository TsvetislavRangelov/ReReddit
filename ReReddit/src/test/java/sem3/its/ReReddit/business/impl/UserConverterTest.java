package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import sem3.its.ReReddit.domain.Enums.Role;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserConverterTest {
    @Test
    void convertUser_ShouldReturnUserConverted(){
        UserEntity userEntity = UserEntity.builder().id(1L).username("whatever").
                email("somemail@gmail.com").build();

        User converted = UserConverter.convert(userEntity);

        assertEquals(userEntity.getId(), converted.getId());
    }
}
