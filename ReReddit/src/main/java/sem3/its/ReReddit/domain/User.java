package sem3.its.ReReddit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sem3.its.ReReddit.domain.Enums.Role;

import javax.validation.constraints.NotNull;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long id;
    @NotNull
    private String username;
    private String password;
    private String email;
    private Role role;
}
