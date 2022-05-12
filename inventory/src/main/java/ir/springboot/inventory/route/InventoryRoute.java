package ir.springboot.inventory.route;

import ir.springboot.inventory.service.InventoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Service
public class InventoryRoute {

    private final InventoryHandler inventoryHandler;

    public InventoryRoute(InventoryHandler inventoryHandler) {
        this.inventoryHandler = inventoryHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryRouterFunction(){
        return nest(path("/inventory"),
                route(POST("/deduct")
                        .and(accept(MediaType.APPLICATION_JSON))
                        .and(contentType(MediaType.APPLICATION_JSON)), inventoryHandler::deductInventory)
                .andRoute(POST("/add")
                        .and(accept(MediaType.APPLICATION_JSON))
                        .and(contentType(MediaType.APPLICATION_JSON)), inventoryHandler::addInventory));
    }
}
