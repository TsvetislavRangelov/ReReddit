package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.CreatePostUseCase;
import sem3.its.ReReddit.business.services.GetPostUseCase;
import sem3.its.ReReddit.business.services.GetPostsByUserIdUseCase;
import sem3.its.ReReddit.business.services.GetPostsUseCase;
import sem3.its.ReReddit.configuration.security.isauthenticated.IsAuthenticated;
import sem3.its.ReReddit.domain.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final GetPostsUseCase getPostsUseCase;
    private final CreatePostUseCase createPostUseCase;
    private final GetPostUseCase getPostUseCase;
    private final GetPostsByUserIdUseCase getPostsByUserIdUseCase;

    @GetMapping
    public ResponseEntity<GetPostsResponse> getPosts(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size){
        GetPostsResponse res = getPostsUseCase.getPosts(page, size);
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

    @IsAuthenticated
    @RolesAllowed({"ROLE_STANDARD", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Validated CreatePostRequest request){
        CreatePostResponse res = createPostUseCase.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }


    @GetMapping("/user")
    @RolesAllowed({"ROLE_STANDARD", "ROLE_ADMIN"})
    @IsAuthenticated
    public ResponseEntity<GetPostsByUserIdResponse> getPostsByUserId(@RequestParam(value = "user", required=true) long userId){
        GetPostsByUserIdResponse res = getPostsByUserIdUseCase.getPostsByUserId(userId);
        return ResponseEntity.ok(res);
    }
}
