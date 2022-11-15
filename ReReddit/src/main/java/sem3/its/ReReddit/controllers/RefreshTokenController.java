package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sem3.its.ReReddit.business.exception.RefreshTokenException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.services.AccessTokenEncoder;
import sem3.its.ReReddit.business.services.GetUserUseCase;
import sem3.its.ReReddit.business.services.RefreshTokenService;
import sem3.its.ReReddit.domain.*;
import sem3.its.ReReddit.persistence.entity.RefreshTokenEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/refresh")
public class RefreshTokenController {
    private RefreshTokenService refreshTokenService;
    private final GetUserUseCase getUserUseCase;
    private AccessTokenEncoder encoder;

    @PostMapping
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request){
        String requestRefreshToken = request.getRefreshToken();

        Optional<RefreshTokenEntity> token = Optional.ofNullable(refreshTokenService
                .findByToken(requestRefreshToken)
                .orElseThrow(
                () -> new RefreshTokenException(requestRefreshToken, "Refresh token not found")));
        Optional<User> user = getUserUseCase.getUser(token.get().getUser().getId());
        if(user.isEmpty()){
            throw new ResourceDoesNotExistException();
        }
       refreshTokenService.verifyExpiration(token.get());
        String newToken = encoder.encode(AccessToken.builder()
                .subject(user.get().getUsername())
                .roles(List.of("STANDARD"))
                .userId(user.get().getId())
                .build());
        return ResponseEntity.ok(new RefreshTokenResponse(newToken, requestRefreshToken));

    }
}
