package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.CountNewUsersForDayUseCase;
import sem3.its.ReReddit.persistence.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Service
@AllArgsConstructor
public class CountNewUsersForDayUseCaseImpl implements CountNewUsersForDayUseCase {

    private final UserRepository userRepository;


    @Override
    public long getNewUsersForDay(String date) {


        return userRepository.countAllByRegisteredAt(LocalDate.parse(date));
    }
}
