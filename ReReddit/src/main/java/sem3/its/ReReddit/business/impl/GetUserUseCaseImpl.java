package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.GetUserUseCase;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;

    @Override
    public Optional<User> getUser(long id){
        Optional<User> userOptional = userRepository.findById(id).map(UserConverter::convert);
        if(userOptional.isEmpty()){
            throw new ResourceDoesNotExistException();
        }
        return userOptional;

    }
}
