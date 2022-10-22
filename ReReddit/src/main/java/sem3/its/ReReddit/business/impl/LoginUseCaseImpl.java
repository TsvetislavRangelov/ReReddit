package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.InvalidCredentialsException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.security.PasswordVerifier;
import sem3.its.ReReddit.business.services.AccessTokenEncoder;
import sem3.its.ReReddit.business.services.LoginUseCase;
import sem3.its.ReReddit.domain.*;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private AccessTokenEncoder encoder;
    private UserRepository userRepository;
    private PasswordVerifier passwordVerifier;

    public LoginResponse login(LoginRequest request){
        char[] pwd = request.getPassword().toCharArray();
       Optional<UserEntity> userOptional = userRepository.findByEmail(request.getEmail());

        if(userOptional.isEmpty()){
            throw new ResourceDoesNotExistException();
        }

        if(!passwordVerifier.verify(userOptional.get().getPassword(), pwd)){
          throw new InvalidCredentialsException();
        }
        UserEntity user = userOptional.get();
        String token = generateAccessToken(user);
        return LoginResponse.builder()
                .token(token)
                .build();

    }

    private String generateAccessToken(UserEntity user){
        Long id = user != null ? user.getId() : null;
        List<String> roles = user.getUserRoles().stream().map(userRole -> userRole.getRole().toString()).toList();

        return encoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .userId(id)
                        .build());
    }
}
