package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.CountNewUsersForDayUseCase;
import sem3.its.ReReddit.persistence.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@AllArgsConstructor
public class CountNewUsersForDayUseCaseImpl implements CountNewUsersForDayUseCase {

    private final UserRepository userRepository;


    @Override
    public long getNewUsersForDay(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date actualDate = formatter.parse(date);
        return userRepository.countAllByRegisteredAt(actualDate);
    }
}
