package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.CreateCommentUseCase;
import sem3.its.ReReddit.business.services.GetCommentsUseCase;
import sem3.its.ReReddit.domain.CreateCommentRequest;
import sem3.its.ReReddit.domain.CreateCommentResponse;
import sem3.its.ReReddit.domain.GetCommentsRequest;
import sem3.its.ReReddit.domain.GetCommentsResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    private final CreateCommentUseCase createCommentUseCase;
    private final GetCommentsUseCase getCommentsUseCase;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody @Validated CreateCommentRequest request){
        CreateCommentResponse res = createCommentUseCase.createComment(request);

        return ResponseEntity.ok(res);
    }
    @GetMapping
    public ResponseEntity<GetCommentsResponse> getComments(@RequestParam(value = "post") long postId){
        GetCommentsRequest request = GetCommentsRequest.builder().postId(postId).build();
        GetCommentsResponse res = getCommentsUseCase.getComments(request);
        return ResponseEntity.ok(res);
    }
}
