package ir.springboot.order.service;

import ir.springboot.common.dto.OrchestratorRequestDto;
import ir.springboot.common.dto.OrderRequestDto;
import ir.springboot.common.dto.OrderResponseDto;
import ir.springboot.common.enums.OrderStatus;
import ir.springboot.order.entity.Order;
import ir.springboot.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final Map<Integer, Double> PRODUCT_PRICE =  Map.of(
            1, 100d,
            2, 200d,
            3, 300d
    );


    private final OrderRepository orderRepository;

    private final FluxSink<OrchestratorRequestDto> sink;

    public OrderService(OrderRepository orderRepository, FluxSink<OrchestratorRequestDto> sink) {
        this.orderRepository = orderRepository;
        this.sink = sink;
    }

    public Mono<Order> createOrder(OrderRequestDto orderRequestDto){
        orderRequestDto.setOrderId(UUID.randomUUID());
        Order order = this.orderRepository.save(this.dtoToEntity(orderRequestDto));
        this.sink.next(this.getOrchestratorRequestDTO(orderRequestDto));
        return Mono.just(order);
    }

//    public Mono<List<OrderResponseDto>> getAll(){
//        return this.orderRepository.findAll()
//                .stream()
//                .map(this::entityToDto)
//                .collect(Collectors.toList());
//    }

    private Order dtoToEntity(final OrderRequestDto dto){
        Order purchaseOrder = new Order();
        purchaseOrder.setOrderId(dto.getOrderId());
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setStatus(OrderStatus.CREATE);
        purchaseOrder.setPrice(PRODUCT_PRICE.get(purchaseOrder.getProductId()));
        return purchaseOrder;
    }

    private OrderResponseDto entityToDto(final Order purchaseOrder){
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(purchaseOrder.getOrderId());
        dto.setProductId(purchaseOrder.getProductId());
        dto.setUserId(purchaseOrder.getUserId());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setAmount(purchaseOrder.getPrice());
        return dto;
    }

    public OrchestratorRequestDto getOrchestratorRequestDTO(OrderRequestDto orderRequestDTO){
        OrchestratorRequestDto requestDTO = new OrchestratorRequestDto();
        requestDTO.setUserId(orderRequestDTO.getUserId());
        requestDTO.setAmount(PRODUCT_PRICE.get(orderRequestDTO.getProductId()));
        requestDTO.setOrderId(orderRequestDTO.getOrderId());
        requestDTO.setProductId(orderRequestDTO.getProductId());
        return requestDTO;
    }
}
