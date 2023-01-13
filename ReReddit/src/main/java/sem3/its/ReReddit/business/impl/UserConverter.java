package sem3.its.ReReddit.business.impl;


import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.sql.Date;


public class UserConverter {
    private UserConverter(){

    }

    public static User convert(UserEntity entity){
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .registeredAt(entity.getRegisteredAt())
                .build();
    }
}
