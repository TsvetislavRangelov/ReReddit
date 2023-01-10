package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.services.GetPostsUseCase;
import sem3.its.ReReddit.domain.GetPostsResponse;
import sem3.its.ReReddit.domain.Post;
import sem3.its.ReReddit.persistence.PostRepository;
import sem3.its.ReReddit.persistence.entity.PostEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetPostsUseCaseImpl implements GetPostsUseCase {
    private PostRepository postRepository;


        @Override
        public GetPostsResponse getPosts(){
            List<PostEntity> results;
            results = postRepository.findAllByOrderByIdDesc();

            final GetPostsResponse res = new GetPostsResponse();
            List<Post> posts = results
                    .stream()
                    .map(PostConverter::convert)
                    .collect(Collectors.toList());
            res.setPosts(posts);
            return res;

        }
}
