package ir.springboot.order.config;

import ir.springboot.common.dto.OrchestratorRequestDto;
import ir.springboot.common.dto.OrderRequestDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderConfig {

    @Bean
     public Sinks.Many<OrderRequestDto> orderSinks(){
         return Sinks.many().multicast().onBackpressureBuffer();
     }

     public Supplier<Flux<OrderRequestDto>> orderSupplier(Sinks.Many<OrderRequestDto> sinks){
        return sinks::asFlux;
     }
}
