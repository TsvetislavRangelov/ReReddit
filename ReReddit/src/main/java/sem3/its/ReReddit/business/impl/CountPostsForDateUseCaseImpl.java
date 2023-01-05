package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.CountPostsForDateUseCase;
import sem3.its.ReReddit.persistence.PostRepository;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class CountPostsForDateUseCaseImpl implements CountPostsForDateUseCase {
    private PostRepository postRepository;

    @Override
    public long getPostCount(String date){
        return postRepository.countAllByCreatedAt(LocalDate.parse(date));
    }
}
