package sem3.its.ReReddit.domain;

import lombok.Builder;
import lombok.Data;
import sem3.its.ReReddit.persistence.entity.CommentEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

@Data
@Builder
public class CreateCommentRequest {
   private Long author_id;
   private String body;
   private Long parent_id;
   private Long post_id;
}
