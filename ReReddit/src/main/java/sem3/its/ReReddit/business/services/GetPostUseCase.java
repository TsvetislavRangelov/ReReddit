package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.Post;

import java.util.Optional;

public interface GetPostUseCase {
    Optional<Post> getPost(long id);
}
