package ir.springboot.common.dto;

import ir.springboot.common.enums.PaymentStatus;
import lombok.*;

import java.util.UUID;

@Data
public class PaymentResponseDto {

    private Integer userId;
    private UUID orderId;
    private Double amount;
    private PaymentStatus status;
}
