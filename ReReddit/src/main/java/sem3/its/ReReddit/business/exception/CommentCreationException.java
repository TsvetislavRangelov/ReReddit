package sem3.its.ReReddit.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CommentCreationException extends ResponseStatusException {
    public CommentCreationException(String errorCause) {super(HttpStatus.BAD_REQUEST, errorCause);}
}
