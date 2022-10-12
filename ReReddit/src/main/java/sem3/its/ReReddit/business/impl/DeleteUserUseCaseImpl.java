package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.DeleteUserUseCase;
import sem3.its.ReReddit.persistence.UserRepository;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    @Override
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }
}
