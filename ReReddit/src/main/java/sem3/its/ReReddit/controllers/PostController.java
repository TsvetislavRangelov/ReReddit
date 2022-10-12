package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.CreatePostUseCase;
import sem3.its.ReReddit.business.services.GetPostsUseCase;
import sem3.its.ReReddit.domain.CreatePostRequest;
import sem3.its.ReReddit.domain.CreatePostResponse;
import sem3.its.ReReddit.domain.GetPostsResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final GetPostsUseCase getPostsUseCase;
    private final CreatePostUseCase createPostUseCase;

    @GetMapping
    public ResponseEntity<GetPostsResponse> getPosts(){
        GetPostsResponse res = getPostsUseCase.getPosts();
        return ResponseEntity.ok(res);
    }
    @PostMapping
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Validated CreatePostRequest request){
        CreatePostResponse res = createPostUseCase.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
