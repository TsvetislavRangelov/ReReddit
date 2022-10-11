package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.UpdateUserUseCase;
import sem3.its.ReReddit.business.exception.InvalidUserException;
import sem3.its.ReReddit.business.exception.UsernameAlreadyExistsException;
import sem3.its.ReReddit.domain.UpdateUserRequest;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private UserRepository userRepository;

    public void updateUser(UpdateUserRequest request){
        Optional<UserEntity> userOptional = userRepository.findById(request.getId());
        if(userOptional.isEmpty()){
            throw new InvalidUserException("USER_ID_INVALID");
        }
        else if(userRepository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException();
        }
        UserEntity user = userOptional.get();
        setFields(request, user);
    }

    private void setFields(UpdateUserRequest request, UserEntity user){
        user.setUsername(request.getUsername());

        userRepository.save(user);
    }
}
