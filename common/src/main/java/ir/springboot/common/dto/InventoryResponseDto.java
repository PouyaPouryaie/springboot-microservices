package ir.springboot.common.dto;

import ir.springboot.common.enums.InventoryStatus;
import lombok.*;

import java.util.UUID;

@Data
public class InventoryResponseDto {

    UUID orderId;
    Long productId;
    Long volume;
    InventoryStatus status;
}
