package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.CreatePostUseCase;
import sem3.its.ReReddit.business.services.GetPostUseCase;
import sem3.its.ReReddit.business.services.GetPostsUseCase;
import sem3.its.ReReddit.domain.CreatePostRequest;
import sem3.its.ReReddit.domain.CreatePostResponse;
import sem3.its.ReReddit.domain.GetPostsResponse;
import sem3.its.ReReddit.domain.Post;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final GetPostsUseCase getPostsUseCase;
    private final CreatePostUseCase createPostUseCase;
    private final GetPostUseCase getPostUseCase;

    @GetMapping
    public ResponseEntity<GetPostsResponse> getPosts(){
        GetPostsResponse res = getPostsUseCase.getPosts();
        return ResponseEntity.ok(res);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPost(@PathVariable(value = "id") final long id){
        final Optional<Post> postOptional = getPostUseCase.getPost(id);
        if(postOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(postOptional.get());
    }
    @PostMapping
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Validated CreatePostRequest request){
        CreatePostResponse res = createPostUseCase.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
