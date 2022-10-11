package sem3.its.ReReddit.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.LoginUseCase;
import sem3.its.ReReddit.domain.LoginRequest;
import sem3.its.ReReddit.domain.LoginResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest request){
        LoginResponse res = loginUseCase.login(request);

        return res != null ? ResponseEntity.status(HttpStatus.OK).body(res) : null ;


    }
}
