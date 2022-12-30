package sem3.its.ReReddit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcement")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private UserEntity sender;
    @Column
    private String title;
    @Column
    private String body;
    @Column
    private LocalDateTime timestamp;
}
