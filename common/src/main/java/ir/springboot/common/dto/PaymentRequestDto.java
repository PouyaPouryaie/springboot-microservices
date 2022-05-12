package ir.springboot.common.dto;

import lombok.*;

import java.util.UUID;

@Data
public class PaymentRequestDto {

    private UUID orderId;
    private Integer userId;
    private Double amount;
}
