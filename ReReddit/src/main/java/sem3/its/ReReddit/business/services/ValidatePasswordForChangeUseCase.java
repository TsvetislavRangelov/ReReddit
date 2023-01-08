package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.ValidatePasswordForChangeRequest;

public interface ValidatePasswordForChangeUseCase {
    boolean validate(ValidatePasswordForChangeRequest request);
}
