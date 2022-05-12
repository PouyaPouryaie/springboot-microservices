package ir.springboot.inventory.service;

import ir.springboot.common.dto.InventoryRequestDto;
import ir.springboot.common.dto.InventoryResponseDto;
import ir.springboot.common.enums.InventoryStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    private Map<Integer, Integer> productInventoryMap;

    @PostConstruct
    private void init(){
        this.productInventoryMap = new HashMap<>();
        this.productInventoryMap.put(1, 5);
        this.productInventoryMap.put(2, 5);
        this.productInventoryMap.put(3, 5);
    }

    public Mono<InventoryResponseDto> deductInventory(final InventoryRequestDto requestDTO){
        int quantity = this.productInventoryMap.getOrDefault(requestDTO.getProductId(), 0);
        InventoryResponseDto responseDTO = new InventoryResponseDto();
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setProductId(requestDTO.getProductId());
        responseDTO.setStatus(InventoryStatus.UNAVAILABLE);
        if(quantity > 0){
            responseDTO.setStatus(InventoryStatus.AVAILABLE);
            this.productInventoryMap.put(requestDTO.getProductId(), quantity - 1);
        }
        return Mono.just(responseDTO);
    }

    public Mono<String> addInventory(final InventoryRequestDto requestDTO){
        this.productInventoryMap
                .computeIfPresent(requestDTO.getProductId(), (k, v) -> v + 1);
        return Mono.empty();
    }
}
