package sem3.its.ReReddit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sem3.its.ReReddit.domain.Enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Column(name = "email")
    private String email;
    @NotBlank
    @Column(name = "username")
    private String username;
    @NotBlank
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private Role role;
}
