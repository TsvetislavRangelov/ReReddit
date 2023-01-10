package sem3.its.ReReddit.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vote")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PostEntity post;
    @Column
    private char type;
}
