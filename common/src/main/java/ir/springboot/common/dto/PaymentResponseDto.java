package ir.springboot.common.dto;

import ir.springboot.common.enums.PaymentStatus;
import lombok.*;

import java.util.UUID;

@Data
public class PaymentResponseDto {

    UUID orderId;
    Long userId;
    Long amount;
    PaymentStatus status;
}
