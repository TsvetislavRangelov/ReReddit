package sem3.its.ReReddit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountLogActivityForDateRequest {
    private Date timestamp;
}
