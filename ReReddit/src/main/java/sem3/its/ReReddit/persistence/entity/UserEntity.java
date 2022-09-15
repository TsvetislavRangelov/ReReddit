package sem3.its.ReReddit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sem3.its.ReReddit.domain.Enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEntity {
    private Long id;
    private String username;
    private Role role;
}
