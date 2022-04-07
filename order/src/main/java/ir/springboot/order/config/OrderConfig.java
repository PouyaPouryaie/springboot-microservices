package ir.springboot.order.config;

import ir.springboot.common.dto.OrchestratorRequestDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxSink;

@Configuration
public class OrderConfig {

    @Bean
     public DirectProcessor<OrchestratorRequestDto> orderSinks(){
         return DirectProcessor.create();
     }

     @Bean
     public FluxSink<OrchestratorRequestDto> orderSupplier(DirectProcessor<OrchestratorRequestDto> publisher){
        return publisher.sink();
     }
}
