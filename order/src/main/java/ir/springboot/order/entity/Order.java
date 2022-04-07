package ir.springboot.order.entity;

import ir.springboot.common.enums.OrderStatus;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    private UUID orderId;
    private Long userId;
    private Long productId;
    private Double price;
    private OrderStatus status;
}
