package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.CreateCommentUseCase;
import sem3.its.ReReddit.domain.CreateCommentRequest;
import sem3.its.ReReddit.domain.CreateCommentResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody @Validated CreateCommentRequest request){
        CreateCommentResponse res = createCommentUseCase.createComment(request);

        return ResponseEntity.ok(res);
    }
}
