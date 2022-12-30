package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.CreateLogActivityRequest;
import sem3.its.ReReddit.domain.CreateLogActivityResponse;

public interface CreateLogActivityUseCase {
    CreateLogActivityResponse createLogActivity(CreateLogActivityRequest request);
}
