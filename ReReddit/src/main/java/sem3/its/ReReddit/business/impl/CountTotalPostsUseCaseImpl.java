package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.CountTotalPostsUseCase;
import sem3.its.ReReddit.persistence.PostRepository;

@AllArgsConstructor
@Service
public class CountTotalPostsUseCaseImpl implements CountTotalPostsUseCase {
    private PostRepository postRepository;
    @Override
    public long countTotalPosts() {
        return postRepository.count();
    }
}
