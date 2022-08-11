package ir.springboot.common.dto;

import lombok.*;

import java.util.UUID;

@Data
public class OrchestratorRequestDto {

    UUID orderId;
    Integer userId;
    Integer productId;
    Double amount;
    Long volume;
}
