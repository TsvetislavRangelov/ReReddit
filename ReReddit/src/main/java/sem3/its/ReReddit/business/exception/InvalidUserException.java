package sem3.its.ReReddit.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserException extends ResponseStatusException {
    public InvalidUserException(String err){super(HttpStatus.BAD_REQUEST, err);}
}
