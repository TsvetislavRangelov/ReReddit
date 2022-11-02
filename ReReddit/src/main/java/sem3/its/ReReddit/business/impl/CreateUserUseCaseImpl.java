package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.CreateUserUseCase;
import sem3.its.ReReddit.business.exception.InvalidRequestBodyException;
import sem3.its.ReReddit.business.exception.UsernameAlreadyExistsException;
import sem3.its.ReReddit.domain.CreateUserRequest;
import sem3.its.ReReddit.domain.CreateUserResponse;
import sem3.its.ReReddit.domain.Enums.Role;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;
import sem3.its.ReReddit.business.security.PasswordHasher;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    private final PasswordHasher passwordHasher;


    @Override
    public CreateUserResponse createUser(CreateUserRequest request){
        if(userRepository.existsByUsername(request.getUsername()) ||
                userRepository.existsByEmail(request.getEmail())){
            throw new UsernameAlreadyExistsException();
        }

        UserEntity createdUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .id(createdUser.getId())
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request){
        String pwd = request.getPassword();
        if(pwd.length() == 0){
            throw new InvalidRequestBodyException();
        }
            String hash = passwordHasher.hash(pwd);

                UserEntity userEntity = UserEntity.builder()
                        .username(request.getUsername())
                        .password(hash)
                        .email(request.getEmail())
                        .role(Role.STANDARD)
                        .build();
                return userRepository.save(userEntity);
        }
    }

