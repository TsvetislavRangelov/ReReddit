package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.GetUsersUseCase;
import sem3.its.ReReddit.domain.GetUsersResponse;
import sem3.its.ReReddit.domain.User;
import sem3.its.ReReddit.persistence.UserRepository;
import sem3.its.ReReddit.persistence.entity.UserEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {

    private UserRepository userRepository;

    @Override
    public GetUsersResponse getUsers(){
        List<UserEntity> results;
        results = userRepository.findAll();

        final GetUsersResponse res = new GetUsersResponse();
        List<User> users =results
                .stream()
                .map(UserConverter::convert)
                .toList();
        res.setUsers(users);

        return res;
    }
}
