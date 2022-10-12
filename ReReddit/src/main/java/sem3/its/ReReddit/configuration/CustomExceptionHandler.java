package sem3.its.ReReddit.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sem3.its.ReReddit.business.exception.InvalidRequestBodyException;
import sem3.its.ReReddit.business.exception.PostHasNoAuthorException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.exception.UsernameAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> HandleUsernameAlreadyExistsException(UsernameAlreadyExistsException exception){
        Map<String, String> err = new HashMap<>();
        err.put("message", exception.getLocalizedMessage());
        err.put("status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    public ResponseEntity<Map<String,String>> HandleResourceDoesNotExistException(ResourceDoesNotExistException exception){
        Map<String, String> err = new HashMap<>();
        err.put("timestamp", LocalDateTime.now().toString());
        err.put("message", exception.getLocalizedMessage());
        err.put("status", HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<Map<String, String>> HandleInvalidRequestBodyException(InvalidRequestBodyException exception){
        return InvalidRequestBody(exception);
    }

    @ExceptionHandler(PostHasNoAuthorException.class)
        public ResponseEntity<Map<String, String>> HandlePostHasNoAuthorException(PostHasNoAuthorException exception){
            return InvalidRequestBody(exception);
        }

        private ResponseEntity<Map<String,String>> InvalidRequestBody(ResponseStatusException exception){
            Map<String,String> err = new HashMap<>();
            err.put("timestamp", LocalDateTime.now().toString());
            err.put("message", exception.getLocalizedMessage());
            err.put("status", HttpStatus.BAD_REQUEST.toString());

            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
}
