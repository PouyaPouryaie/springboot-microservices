package ir.springboot.common.dto;

import ir.springboot.common.enums.OrderStatus;
import lombok.*;

import java.util.UUID;

@Data
public class OrchestratorResponseDto {

    UUID orderId;
    Integer userId;
    Integer productId;
    Double amount;
    Long volume;
    OrderStatus status;
}
