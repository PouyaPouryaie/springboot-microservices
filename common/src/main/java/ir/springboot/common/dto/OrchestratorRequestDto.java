package ir.springboot.common.dto;

import lombok.*;

import java.util.UUID;

@Data
public class OrchestratorRequestDto {

    UUID orderId;
    Long userId;
    Long productId;
    Double amount;
    Long volume;
}
