package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken token);
}
