package sem3.its.ReReddit.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RefreshTokenException extends ResponseStatusException {
     private static final long serialVersionUID = 1L;

     public RefreshTokenException(String token, String message){
         super(HttpStatus.FORBIDDEN, String.format("Failed for [%s]: %s", token, message));
     }
}
