package ir.springboot.common.dto;

import lombok.*;

import java.util.UUID;

@Data
public class PaymentRequestDto {

    UUID orderId;
    Long userId;
    Long amount;
}
