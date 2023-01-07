package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.services.GetPasswordForUserUseCase;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetPasswordForUserUseCaseImpl implements GetPasswordForUserUseCase {
    private UserRepository userRepository;

    @Override
    public String getPassword(long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new ResourceDoesNotExistException();
        }
        return user.get().getPassword();
    }
}
