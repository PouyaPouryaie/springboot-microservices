package ir.springboot.common.dto;

import lombok.*;

import java.util.UUID;

@Data
public class OrderRequestDto {

    UUID orderId;
    Long userId;
    Long productId;
    Long amount;
    Long volume;
}
