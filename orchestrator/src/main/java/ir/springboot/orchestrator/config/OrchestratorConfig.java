package ir.springboot.orchestrator.config;

import ir.springboot.common.dto.OrchestratorRequestDto;
import ir.springboot.common.dto.OrchestratorResponseDto;
import ir.springboot.orchestrator.service.OrchestratorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class OrchestratorConfig {

    private final OrchestratorService orchestratorService;

    public OrchestratorConfig(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @Bean
    public Function<Flux<OrchestratorRequestDto>, Flux<OrchestratorResponseDto>> processor(){
        return orchestratorRequestDtoFlux -> orchestratorRequestDtoFlux
                .flatMap(this.orchestratorService::orderProduct)
                .doOnNext(responseDto -> System.out.println("status : " + responseDto.getStatus()));
    }
}
