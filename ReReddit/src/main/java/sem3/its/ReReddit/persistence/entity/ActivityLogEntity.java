package sem3.its.ReReddit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_log")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActivityLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private LocalDateTime timestamp;

    @JoinTable(name = "user", joinColumns = @JoinColumn(name = "profile", referencedColumnName = "username"))
    private String profile;
    @Column
    private boolean success;


}
