package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(long id);
}
