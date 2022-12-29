package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.CountLogActivityForDateRequest;
import sem3.its.ReReddit.domain.CountLogActivityForDateResponse;


public interface CountLogActivityForDateUseCase {
    CountLogActivityForDateResponse countLogActivityForDate(CountLogActivityForDateRequest request);
}
