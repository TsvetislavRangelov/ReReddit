package sem3.its.ReReddit.business.impl;

import sem3.its.ReReddit.domain.RefreshToken;
import sem3.its.ReReddit.persistence.entity.RefreshTokenEntity;

public class RefreshTokenConverter {
    private RefreshTokenConverter(){}

    public static RefreshToken convert(RefreshTokenEntity token){
        return RefreshToken.builder()
                .token(token.getToken())
                .expiryDate(token.getExpiryDate())
                .id(token.getUser().getId())
                .user(UserConverter.convert(token.getUser()))
                .build();
    }
}
