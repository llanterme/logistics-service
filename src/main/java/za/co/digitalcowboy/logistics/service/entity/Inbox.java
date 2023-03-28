package za.co.digitalcowboy.logistics.service.entity;


import lombok.*;
import org.hibernate.annotations.Type;
import za.co.digitalcowboy.logistics.service.domain.MessageType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inbox")
public class Inbox extends Auditable {

  @Column(name = "event_time", nullable = false, updatable = false)
  protected LocalDateTime eventTime;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "event_id", nullable = false)
  @Type(type = "uuid-char")
  private UUID eventId;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private MessageType type;

  @Column(name = "payload")
  private String payload;

  @Column(name = "note")
  private String note;

  @Column(name = "is_read")
  private boolean isRead;
}
