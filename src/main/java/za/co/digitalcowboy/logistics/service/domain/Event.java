package za.co.digitalcowboy.logistics.service.domain;

import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event<D, C> {


  @VerifyUuid
  private String id;

  @NotBlank
  private String version;

  @NotNull
  private MessageType type;

  @CurrentDateTimeRange(numberOfDays = 30)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @NotNull
  private LocalDateTime time;

  @NotBlank
  private String source;

  @Valid
  @NotNull
  private D detail;

  @Valid
  private C context;


  private String asyncMessageId;
}
