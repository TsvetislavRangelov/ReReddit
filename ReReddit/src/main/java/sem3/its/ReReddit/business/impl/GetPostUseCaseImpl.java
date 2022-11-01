package sem3.its.ReReddit.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.its.ReReddit.business.exception.ResourceDoesNotExistException;
import sem3.its.ReReddit.business.services.GetPostUseCase;
import sem3.its.ReReddit.domain.Post;
import sem3.its.ReReddit.persistence.PostRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetPostUseCaseImpl implements GetPostUseCase {
    private PostRepository postRepository;

    @Override
    public Optional<Post> getPost(long id){
        Optional<Post> postOptional = postRepository.findById(id).map(PostConverter::convert);
        if(postOptional.isEmpty()){
            throw new ResourceDoesNotExistException();
        }
        return postOptional;
    }
}
