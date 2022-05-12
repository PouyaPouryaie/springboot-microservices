package ir.springboot.order.entity;

import ir.springboot.common.enums.OrderStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@ToString
public class Order {

    @Id
    private UUID orderId;
    private Long userId;
    private Long productId;
    private Double price;
    private OrderStatus status;
}
