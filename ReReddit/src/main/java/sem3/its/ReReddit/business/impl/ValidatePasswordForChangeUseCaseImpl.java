package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.InvalidCredentialsException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.security.PasswordVerifier;
import sem3.its.ReReddit.business.services.ValidatePasswordForChangeUseCase;
import sem3.its.ReReddit.domain.ValidatePasswordForChangeRequest;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ValidatePasswordForChangeUseCaseImpl implements ValidatePasswordForChangeUseCase {
    private PasswordVerifier passwordVerifier;
    private UserRepository userRepository;



    @Override
    public boolean validate(ValidatePasswordForChangeRequest request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        if(user.isEmpty()){
            throw new ResourceDoesNotExistException();
        }
        if(!passwordVerifier.verify(user.get().getPassword(), request.getOldPassword().toCharArray())){
            throw new InvalidCredentialsException();
        }
        return true;
    }
}
