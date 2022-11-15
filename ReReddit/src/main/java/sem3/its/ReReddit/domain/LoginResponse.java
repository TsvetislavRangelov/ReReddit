package sem3.its.ReReddit.domain;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private Long id;
    private String username;
    private List<String> roles;
}
