package ir.springboot.inventory.route;

import ir.springboot.common.dto.InventoryRequestDto;
import ir.springboot.common.dto.InventoryResponseDto;
import ir.springboot.common.utils.ResponseUtils;
import ir.springboot.inventory.service.InventoryService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class InventoryHandler {

    private final InventoryService service;

    public InventoryHandler(InventoryService service) {
        this.service = service;
    }

    public Mono<ServerResponse> deductInventory(ServerRequest request){
        return request.bodyToMono(InventoryRequestDto.class)
                .flatMap(service::deductInventory)
                .flatMap(inventoryResponseDto -> ResponseUtils.ok(inventoryResponseDto, InventoryResponseDto.class));
    }

    public Mono<ServerResponse> addInventory(ServerRequest request) {
        return request.bodyToMono(InventoryRequestDto.class)
                .flatMap(service::addInventory)
                .flatMap(ignore -> ResponseUtils.ok());
    }

}
