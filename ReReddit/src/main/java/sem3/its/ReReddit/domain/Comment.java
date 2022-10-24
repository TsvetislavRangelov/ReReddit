package sem3.its.ReReddit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Comment {
    private Long id;
    private User author;
    private String body;
    private Post post;
    private int ups;
    private int downs;
    private Comment parent;
}
