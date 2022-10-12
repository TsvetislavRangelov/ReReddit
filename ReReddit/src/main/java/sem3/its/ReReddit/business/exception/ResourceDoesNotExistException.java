package sem3.its.ReReddit.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceDoesNotExistException extends ResponseStatusException {
    public ResourceDoesNotExistException(){super(HttpStatus.NOT_FOUND, "RESOURCE_DOESNT_EXIST");}
}
