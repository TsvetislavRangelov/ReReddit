package sem3.its.ReReddit.domain;

import lombok.Builder;
import lombok.Data;
import sem3.its.ReReddit.persistence.entity.CommentEntity;
import sem3.its.ReReddit.persistence.entity.PostEntity;
import sem3.its.ReReddit.persistence.entity.UserEntity;

@Data
@Builder
public class CreateCommentRequest {
   private UserEntity author;
   private String body;
   private PostEntity post;
}
