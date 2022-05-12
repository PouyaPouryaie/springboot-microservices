package ir.springboot.order.route;

import ir.springboot.common.dto.OrderRequestDto;
import ir.springboot.common.dto.OrderResponseDto;
import ir.springboot.order.ResponseUtils;
import ir.springboot.order.entity.Order;
import ir.springboot.order.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.UUID;

@Component
public class OrderHandler {

    private final OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public Mono<ServerResponse> createOrder(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(OrderRequestDto.class)
                .flatMap(orderService::createOrder)
                .flatMap(order -> ResponseUtils.ok(order, Order.class));
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest){
        return Mono.empty()
                .then(orderService.getAll())
                .flatMap(list -> ResponseUtils.ok(list, List.class));
    }

    public Mono<ServerResponse> hello(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("Hello guys"));
    }

    public Mono<ServerResponse> find(ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("Hello " + id));
    }
}
