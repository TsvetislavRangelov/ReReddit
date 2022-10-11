package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.GetPostsUseCase;
import sem3.its.ReReddit.domain.GetPostsResponse;
import sem3.its.ReReddit.domain.Post;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPostsUseCaseImpl implements GetPostsUseCase {
    private PostRepository postRepository;

    @Override
    public GetPostsResponse getPosts(){
        List<PostEntity> results;
        results = postRepository.findAll();

        final GetPostsResponse res = new GetPostsResponse();
        List<Post> posts = results
                .stream()
                .map(PostConverter::convert)
                .toList();
        res.setPosts(posts);
        return res;
    }
}
