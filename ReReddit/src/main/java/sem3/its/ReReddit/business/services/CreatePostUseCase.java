package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.CreatePostRequest;
import sem3.its.ReReddit.domain.CreatePostResponse;

public interface CreatePostUseCase {
    CreatePostResponse createPost(CreatePostRequest request);
}
