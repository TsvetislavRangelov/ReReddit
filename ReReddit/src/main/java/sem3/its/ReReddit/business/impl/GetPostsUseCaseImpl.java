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
    public GetPostsResponse getPosts(int page, int size){
        List<PostEntity> results;
        Pageable pageable = PageRequest.of(page, size);
        Page<PostEntity> postEntityPage;

        postEntityPage = postRepository.findAll(pageable);

        results = postEntityPage.getContent();

        List<Post> convertedPosts = results.stream().map(PostConverter::convert).toList();

        Map<String, Object> output = new HashMap<>();
        output.put("posts", convertedPosts);
        output.put("currentPage", postEntityPage.getNumber());
        output.put("totalItems", postEntityPage.getTotalElements());
        output.put("totalPages", postEntityPage.getTotalPages());
        //results = postRepository.findAll(pageable);

        final GetPostsResponse res = new GetPostsResponse();
        res.setData(output);
        return res;
    }
}
