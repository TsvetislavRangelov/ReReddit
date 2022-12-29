package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.CountLogActivityForDateUseCase;
import sem3.its.ReReddit.business.services.CreateLogActivityUseCase;
import sem3.its.ReReddit.configuration.security.isauthenticated.IsAuthenticated;
import sem3.its.ReReddit.domain.CountLogActivityForDateRequest;
import sem3.its.ReReddit.domain.CountLogActivityForDateResponse;
import sem3.its.ReReddit.domain.CreateLogActivityRequest;
import sem3.its.ReReddit.domain.CreateLogActivityResponse;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class ActivityLogController {
    private final CreateLogActivityUseCase createLogActivityUseCase;
    private final CountLogActivityForDateUseCase countLogActivityForDateUseCase;
    @PostMapping
    public ResponseEntity<CreateLogActivityResponse> createLogActivity(@RequestBody @Validated CreateLogActivityRequest request){
        CreateLogActivityResponse res = createLogActivityUseCase.createLogActivity(request);

        return ResponseEntity.ok(res);
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/count")
    public ResponseEntity<CountLogActivityForDateResponse> countLogActivityForDate(@RequestBody @Validated CountLogActivityForDateRequest request){
        CountLogActivityForDateResponse res = countLogActivityForDateUseCase.countLogActivityForDate(request);

        return ResponseEntity.ok().body(res);
    }
}
