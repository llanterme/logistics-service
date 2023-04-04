package za.co.digitalcowboy.logistics.service.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@Jacksonized
@ToString(onlyExplicitlyIncluded = true)
public class Order {

    private Long id;
    private UUID orderId;
    private String orderType;
    private String orderMessage;
    private String orderStatus;

    @CurrentDateTimeRange(numberOfDays = 30)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotNull
    private LocalDateTime createdDate;

}
