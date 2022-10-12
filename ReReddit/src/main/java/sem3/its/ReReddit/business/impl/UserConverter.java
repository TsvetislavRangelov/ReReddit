package sem3.its.ReReddit.business.impl;


import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Collections;

public class UserConverter {
    private UserConverter(){

    }

    public static User convert(UserEntity entity){
        return User.builder()
                .id(entity.getId())
                .role(entity.getRole())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
}
