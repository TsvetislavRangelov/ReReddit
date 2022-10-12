package sem3.its.ReReddit.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PostHasNoAuthorException extends ResponseStatusException {
    public PostHasNoAuthorException(){super(HttpStatus.BAD_REQUEST);}
}
