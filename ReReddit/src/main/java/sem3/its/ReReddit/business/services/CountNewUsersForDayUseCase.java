package sem3.its.ReReddit.business.services;

import java.text.ParseException;

public interface CountNewUsersForDayUseCase {
    long getNewUsersForDay(String date) throws ParseException;
}
