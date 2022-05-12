package ir.springboot.order.eventhandler;

import ir.springboot.common.dto.OrchestratorRequestDto;
import ir.springboot.common.dto.OrchestratorResponseDto;
import ir.springboot.order.service.OrderEventUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class OrderEventHandler {

    @Autowired
    private DirectProcessor<OrchestratorRequestDto> source;

    @Autowired
    private OrderEventUpdateService service;

    @Bean
    public Supplier<Flux<OrchestratorRequestDto>> supplier(){
        return () -> Flux.from(source);
    }

    @Bean
    public Consumer<Flux<OrchestratorResponseDto>> consumer(){
        return flux -> flux.subscribe(service::updateOrder);
    }
}
