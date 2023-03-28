package za.co.digitalcowboy.logistics.service.domain;


import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@SuperBuilder
@Jacksonized
@ToString(onlyExplicitlyIncluded = true)
public class Order {

    @ToString.Include
    private Long orderId;
    @ToString.Include
    private String orderType;
    @ToString.Include
    private String orderMessage;
    @ToString.Include
    private String orderStatus;

}
