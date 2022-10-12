package sem3.its.ReReddit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sem3.its.ReReddit.persistence.entity.UserEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatePostRequest {
    private UserEntity author;
    private String header;
    private String body;


}
