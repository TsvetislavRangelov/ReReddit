package sem3.its.ReReddit.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameAlreadyExistsException extends ResponseStatusException {
    public UsernameAlreadyExistsException() {super(HttpStatus.CONFLICT, "USERNAME_ALREADY_EXISTS");}
}
