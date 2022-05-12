package ir.springboot.order.route;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Service
public class OrderRouter {

    private final OrderHandler orderHandler;

    public OrderRouter(OrderHandler orderHandler) {
        this.orderHandler = orderHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> orderRouterFunction(){
        return RouterFunctions.nest(path("/order"),
                route(GET("/hello")
                        .and(accept(MediaType.APPLICATION_JSON)), orderHandler::hello)
                .andRoute(GET("/find/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)), orderHandler::find)
                .andRoute(POST("/create")
                        .and(accept(MediaType.APPLICATION_JSON)), orderHandler::createOrder));
    }
}
