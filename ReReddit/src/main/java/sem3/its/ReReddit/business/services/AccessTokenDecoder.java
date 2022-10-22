package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
