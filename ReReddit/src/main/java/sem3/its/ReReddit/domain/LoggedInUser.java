package sem3.its.ReReddit.domain;

import lombok.Builder;
import lombok.Data;
import sem3.its.ReReddit.domain.Enums.Role;

@Data
@Builder
public class LoggedInUser {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
