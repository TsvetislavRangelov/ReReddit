package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.InvalidCredentialsException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.security.PasswordVerifier;
import sem3.its.ReReddit.business.services.AccessTokenEncoder;
import sem3.its.ReReddit.business.services.LoginUseCase;
import sem3.its.ReReddit.business.services.RefreshTokenService;
import sem3.its.ReReddit.domain.*;
import sem3.its.ReReddit.persistence.RefreshTokenRepository;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private AccessTokenEncoder encoder;
    private UserRepository userRepository;
    private PasswordVerifier passwordVerifier;
    private RefreshTokenRepository refreshTokenRepository;
    private RefreshTokenService refreshTokenService;

    public LoginResponse login(LoginRequest request){
        char[] pwd = request.getPassword().toCharArray();
       Optional<UserEntity> userOptional = userRepository.findByEmail(request.getEmail());

        if(userOptional.isEmpty()){
            throw new ResourceDoesNotExistException();
        }

        if(!passwordVerifier.verify(userOptional.get().getPassword(), pwd)) {
            throw new InvalidCredentialsException();
        }
        UserEntity user = userOptional.get();
        refreshTokenRepository.deleteByUser(user);
        String accessToken = generateAccessToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        List<String> roles = user.getUserRoles()
                .stream()
                .map(r -> r.getRole()
                        .toString())
                .toList();
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .username(user.getUsername())
                .id(user.getId())
                .roles(roles)
                .build();

    }

    private String generateAccessToken(UserEntity user){
        assert user != null;
        Long id = user.getId();
        List<String> roles = user.getUserRoles().stream().map(userRole -> userRole.getRole().toString()).collect(Collectors.toList());

        return encoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .userId(id)
                        .build());
    }
}
