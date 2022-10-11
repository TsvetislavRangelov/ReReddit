package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.security.PasswordVerifier;
import sem3.its.ReReddit.business.services.LoginUseCase;
import sem3.its.ReReddit.domain.LoggedInUser;
import sem3.its.ReReddit.domain.LoginRequest;
import sem3.its.ReReddit.domain.LoginResponse;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.UserRepository;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private UserRepository userRepository;
    private PasswordVerifier passwordVerifier;

    public LoginResponse login(LoginRequest request){
        char[] pwd = request.getPassword().toCharArray();
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail()).map(UserConverter::convert);

        if(userOptional.isEmpty()){
            throw new ResourceDoesNotExistException();
        }

        if(passwordVerifier.verify(userOptional.get().getPassword(), pwd)){
          LoggedInUser loggedInUser =  LoggedInUser.builder()
                    .id(userOptional.get().getId())
                    .username(userOptional.get().getUsername())
                    .email(userOptional.get().getEmail())
                    .role(userOptional.get().getRole())
                    .build();
            return LoginResponse.builder()
                    .loggedIn(loggedInUser)
                    .build();
        }
        return null;

    }
}
