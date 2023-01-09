package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.*;
import sem3.its.ReReddit.configuration.security.isauthenticated.IsAuthenticated;
import sem3.its.ReReddit.domain.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
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
    private final CountPostsForDateUseCase countPostsForDateUseCase;
    private final CountTotalPostsUseCase countTotalPostsUseCase;
    private final UpvotePostUseCase upvotePostUseCase;
    private final DownVotePostUseCase downVotePostUseCase;
    private final GetPreviousVoteUseCase getPreviousVoteUseCase;

    @GetMapping
    public ResponseEntity<GetPostsResponse> getPosts(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size){
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

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/count")
    public ResponseEntity<Long> countPostsForDate(@RequestParam String date){
        long res = countPostsForDateUseCase.getPostCount(date);

        return ResponseEntity.ok(res);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/total")
    public ResponseEntity<Long> countTotalPosts(){
        long res = countTotalPostsUseCase.countTotalPosts();

        return ResponseEntity.ok(res);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STANDARD"})
    @PatchMapping("/upvote")
    public ResponseEntity<Void> upvotePost(@RequestBody @Valid UpvotePostRequest request){
        upvotePostUseCase.upvote(request);
        return ResponseEntity.noContent().build();
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STANDARD"})
    @PatchMapping("/downvote")
    public ResponseEntity<Void> downvotePost(@RequestBody @Valid DownVotePostRequest request){
        downVotePostUseCase.downvote(request);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STANDARD"})
    @PostMapping("/has-voted")
    public ResponseEntity<GetPreviousVoteResponse> getPreviousVote(@RequestBody @Valid GetPreviousVoteRequest request){
        GetPreviousVoteResponse res = getPreviousVoteUseCase.getVote(request);
        return ResponseEntity.ok(res);
    }
}
