package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.its.ReReddit.business.services.*;
import sem3.its.ReReddit.configuration.security.isauthenticated.IsAuthenticated;
import sem3.its.ReReddit.domain.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.*;

import java.text.ParseException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final CountNewUsersForDayUseCase countNewUsersForDayUseCase;

    private final GetTotalUserCountUseCase getTotalUserCountUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final ValidatePasswordForChangeUseCase validatePasswordForChangeUseCase;


    @GetMapping
    public ResponseEntity<GetUsersResponse> getAllUsers(){
    GetUsersResponse res = getUsersUseCase.getUsers();
    return ResponseEntity.ok(res);
    }
    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam(value = "id", required = true) long id){
        final Optional<User> userOptional = getUserUseCase.getUser(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        CreateUserResponse res = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        deleteUserUseCase.deleteUser((id));
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_STANDARD", "ROLE_ADMIN"})
    @PatchMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @RequestBody @Valid UpdateUserRequest request){
        request.setId(id);
        updateUserUseCase.updateUser(request);
        return ResponseEntity.noContent().build();

    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/count")
    public ResponseEntity<Long> getNewUsersForDay(@RequestParam String date) throws ParseException{
        long res = countNewUsersForDayUseCase.getNewUsersForDay(date);
        return ResponseEntity.ok().body(res);
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/count/total")
    public ResponseEntity<Long> getTotalUserCount(){
        long res = getTotalUserCountUseCase.getTotalUserCount();
        return ResponseEntity.ok().body(res);
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STANDARD"})
    @PatchMapping("/update-pass/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable long id, @RequestBody @Valid UpdatePasswordRequest request){
        request.setUserId(id);
        updatePasswordUseCase.updatePassword(request);
        return ResponseEntity.noContent().build();
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STANDARD"})
    @PostMapping("/pass")
    public ResponseEntity<Boolean> verifyPassword(@RequestBody @Valid ValidatePasswordForChangeRequest request){
        boolean res = validatePasswordForChangeUseCase.validate(request);
        return ResponseEntity.ok(res);
    }
}
