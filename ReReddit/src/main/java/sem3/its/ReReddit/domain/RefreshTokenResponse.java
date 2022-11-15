package sem3.its.ReReddit.domain;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;

    public RefreshTokenResponse(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
