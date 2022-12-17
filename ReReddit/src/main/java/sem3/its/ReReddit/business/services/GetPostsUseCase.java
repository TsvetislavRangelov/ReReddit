package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.GetPostsResponse;

public interface GetPostsUseCase {
    GetPostsResponse getPosts(int page, int size);
}
