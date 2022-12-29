package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.domain.CountLogActivityForDateRequest;
import sem3.its.ReReddit.domain.CountLogActivityForDateResponse;

import java.text.ParseException;


public interface CountLogActivityForDateUseCase {
    CountLogActivityForDateResponse countLogActivityForDate(String date) throws ParseException;
}
