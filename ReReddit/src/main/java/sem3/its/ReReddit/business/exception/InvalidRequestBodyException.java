package sem3.its.ReReddit.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidRequestBodyException extends ResponseStatusException {
    public InvalidRequestBodyException() {super(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_BODY");}
}
