package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.LoginRequest;
import sem3.its.ReReddit.domain.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest request);
}
