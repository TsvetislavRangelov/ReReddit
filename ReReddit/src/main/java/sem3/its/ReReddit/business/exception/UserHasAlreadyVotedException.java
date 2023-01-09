package sem3.its.ReReddit.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserHasAlreadyVotedException extends ResponseStatusException {
    public UserHasAlreadyVotedException () {super(HttpStatus.CONFLICT, "USER_ALREADY_VOTED");}
}
