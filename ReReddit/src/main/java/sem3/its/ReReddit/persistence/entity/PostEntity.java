package sem3.its.ReReddit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @NotBlank
    private UserEntity author;
    @Column(name = "header")
    @NotBlank
    private String header;
    @Column(name = "body")
    @NotBlank
    private String body;
    @Column(name = "ups")
    private int ups;
    @Column(name = "downs")
    private int downs;
    @Column(name = "comments")
    private int comments;
    @Column(name = "created_at")
    private LocalDate createdAt;

}
