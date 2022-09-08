package sem3.its.ReReddit.business.impl;


import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.entity.UserEntity;

public class UserConverter {
    private UserConverter(){

    }

    public static User convert(UserEntity entity){
        return User.builder()
                .id(entity.getId())
                .posts(null)
                .password(entity.getPassword())
                .username(entity.getUsername())
                .build();
    }
}
