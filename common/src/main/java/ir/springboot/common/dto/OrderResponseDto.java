package ir.springboot.common.dto;

import ir.springboot.common.enums.OrderStatus;
import lombok.*;

import java.util.UUID;

@Data
public class OrderResponseDto {

    private UUID orderId;
    private Long userId;
    private Long productId;
    private Double amount;
    private OrderStatus status;
}
