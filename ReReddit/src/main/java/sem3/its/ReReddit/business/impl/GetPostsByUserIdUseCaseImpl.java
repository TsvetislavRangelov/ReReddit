package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.GetPostsByUserIdUseCase;
import sem3.its.ReReddit.domain.GetPostsByUserIdResponse;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.domain.Post;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetPostsByUserIdUseCaseImpl implements GetPostsByUserIdUseCase {

    private PostRepository postRepository;

    @Override
    public GetPostsByUserIdResponse  getPostsByUserId(long userId){
        List<PostEntity> results;
        results = postRepository.findAllByAuthorId(userId);

        final GetPostsByUserIdResponse res = new GetPostsByUserIdResponse();

        List<Post> posts = results
                .stream()
                .map(PostConverter::convert)
                .collect(Collectors.toList());

                res.setPosts(posts);
        return res;
    }
}
