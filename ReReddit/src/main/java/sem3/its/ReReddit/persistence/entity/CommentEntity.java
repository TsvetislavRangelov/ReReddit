package sem3.its.ReReddit.persistence.entity;

import lombok.Builder;
import lombok.Data;
import sem3.its.ReReddit.domain.Comment;
import sem3.its.ReReddit.domain.Post;

import java.util.List;

@Data
@Builder
public class CommentEntity {
    private Long id;
    private UserEntity author;
    private String body;
    private Post post;
    private int ups;
    private int downs;
    private CommentEntity parent;
    private List<CommentEntity> children;
}