package ir.springboot.common.dto;

import lombok.*;

import java.util.UUID;

@Data
public class InventoryRequestDto {

    UUID orderId;
    Long productId;
    Long volume;
}
