package ir.springboot.inventory.route;

import ir.springboot.common.dto.OrderRequestDto;
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

    public Mono<ServerResponse> deductInventory(ServerRequest serverRequest){
        return Mono.empty();
    }

    public Mono<ServerResponse> addInventory(ServerRequest serverRequest) {
        return Mono.empty();
    }

}
