package sem3.its.ReReddit.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sem3.its.ReReddit.business.exception.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String HASHMAP_MESSAGE = "message";
    private static final String HASHMAP_TIMESTAMP = "timestamp";
    private static final String HASHMAP_STATUS = "status";
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException exception){
        Map<String, String> err = new HashMap<>();
        err.put(HASHMAP_MESSAGE, exception.getLocalizedMessage());
        err.put(HASHMAP_STATUS, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    public ResponseEntity<Map<String,String>> handleResourceDoesNotExistException(ResourceDoesNotExistException exception){
        Map<String, String> err = new HashMap<>();
        err.put(HASHMAP_TIMESTAMP, LocalDateTime.now().toString());
        err.put(HASHMAP_MESSAGE, exception.getLocalizedMessage());
        err.put(HASHMAP_STATUS, HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRequestBodyException(InvalidRequestBodyException exception){
        return invalidRequestBody(exception);
    }

    @ExceptionHandler(PostHasNoAuthorException.class)
        public ResponseEntity<Map<String, String>> handlePostHasNoAuthorException(PostHasNoAuthorException exception){
            return invalidRequestBody(exception);
        }

        private ResponseEntity<Map<String,String>> invalidRequestBody(ResponseStatusException exception){
            Map<String,String> err = new HashMap<>();
            err.put(HASHMAP_TIMESTAMP, LocalDateTime.now().toString());
            err.put(HASHMAP_MESSAGE, exception.getLocalizedMessage());
            err.put(HASHMAP_STATUS, HttpStatus.BAD_REQUEST.toString());

            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(InvalidCredentialsException exception){
        Map<String, String> err = new HashMap<>();
        err.put(HASHMAP_TIMESTAMP, LocalDateTime.now().toString());
        err.put(HASHMAP_MESSAGE, exception.getMessage());
        err.put(HASHMAP_STATUS, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
}
