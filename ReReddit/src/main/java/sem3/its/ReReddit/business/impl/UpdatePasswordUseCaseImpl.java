package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.InvalidCredentialsException;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.security.PasswordHasher;
import sem3.its.ReReddit.business.security.PasswordVerifier;
import sem3.its.ReReddit.business.services.UpdatePasswordUseCase;
import sem3.its.ReReddit.domain.UpdatePasswordRequest;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdatePasswordUseCaseImpl implements UpdatePasswordUseCase {
    private UserRepository userRepository;
    private PasswordHasher passwordHasher;
    private PasswordVerifier passwordVerifier;
    @Override
    public void updatePassword(UpdatePasswordRequest request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        if(user.isEmpty()){
            throw new ResourceDoesNotExistException();
        }

        String newHash = passwordHasher.hash(request.getNewPassword());
        user.get().setPassword(newHash);
        System.out.println(user.get().getPassword());
        userRepository.save(user.get());

    }
}
