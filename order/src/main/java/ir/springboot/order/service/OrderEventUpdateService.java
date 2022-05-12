package ir.springboot.order.service;

import ir.springboot.common.dto.OrchestratorResponseDto;
import ir.springboot.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderEventUpdateService {

    private final OrderRepository orderRepository;

    public OrderEventUpdateService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void updateOrder(final OrchestratorResponseDto responseDto){
        orderRepository.findById(responseDto.getOrderId())
                .ifPresent(po ->
                {
                    po.setStatus(responseDto.getStatus());
                    orderRepository.save(po);
                });
    }
}
