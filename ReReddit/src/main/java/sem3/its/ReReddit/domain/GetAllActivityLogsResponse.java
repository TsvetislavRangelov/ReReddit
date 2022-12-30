package sem3.its.ReReddit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllActivityLogsResponse {
    private Long id;
    private Date timestamp;
    private String profile;
    private boolean success;
}
