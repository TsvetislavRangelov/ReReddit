package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.CreateLogActivityUseCase;
import sem3.its.ReReddit.configuration.security.isauthenticated.IsAuthenticated;
import sem3.its.ReReddit.domain.CreateLogActivityRequest;
import sem3.its.ReReddit.domain.CreateLogActivityResponse;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class ActivityLogController {
    private final CreateLogActivityUseCase createLogActivityUseCase;

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<CreateLogActivityResponse> createLogActivity(@RequestBody @Validated CreateLogActivityRequest request){
        CreateLogActivityResponse res = createLogActivityUseCase.createLogActivity(request);

        return ResponseEntity.ok(res);
    }
}
