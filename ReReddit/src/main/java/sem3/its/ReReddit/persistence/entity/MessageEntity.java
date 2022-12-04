package sem3.its.ReReddit.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "sender")
    private String sender;
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "payload")
    private String payload;
}
