package ir.springboot.common.dto;

import ir.springboot.common.enums.OrderStatus;
import lombok.*;

import java.util.UUID;

@Data
public class OrchestratorResponseDto {

    UUID orderId;
    Long userId;
    Long productId;
    Long amount;
    Long volume;
    OrderStatus status;
}
