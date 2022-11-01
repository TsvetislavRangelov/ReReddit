package sem3.its.ReReddit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {
    private String subject;
    private List<String> roles;
    private Long userId;

    @JsonIgnore
    public boolean hasRole(String roleName){
        if(roles == null){
            return false;
        }
        return roles.contains(roleName);
    }
}
