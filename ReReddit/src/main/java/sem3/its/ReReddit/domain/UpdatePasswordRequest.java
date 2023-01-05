package sem3.its.ReReddit.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdatePasswordRequest {
    private long userId;
    private String oldPassword;
    private String newPassword;
}
