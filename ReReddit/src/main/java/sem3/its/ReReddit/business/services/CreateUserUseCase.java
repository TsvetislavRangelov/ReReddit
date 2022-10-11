package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.CreateUserRequest;
import sem3.its.ReReddit.domain.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
