package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.GetPostsByUserIdResponse;

public interface GetPostsByUserIdUseCase {
    GetPostsByUserIdResponse getPostsByUserId(long userId);
}
