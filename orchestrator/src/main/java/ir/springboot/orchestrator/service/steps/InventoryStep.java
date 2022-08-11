package ir.springboot.orchestrator.service.steps;

import ir.springboot.common.dto.InventoryRequestDto;
import ir.springboot.common.dto.InventoryResponseDto;
import ir.springboot.common.enums.InventoryStatus;
import ir.springboot.orchestrator.service.WorkflowStep;
import ir.springboot.orchestrator.service.WorkflowStepStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class InventoryStep implements WorkflowStep {

    private final WebClient webClient;
    private final InventoryRequestDto requestDto;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public InventoryStep(WebClient webClient, InventoryRequestDto requestDto) {
        this.webClient = webClient;
        this.requestDto = requestDto;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Mono<Boolean> process() {
        return this.webClient
                .post()
                .uri("/inventory/deduct")
                .body(BodyInserters.fromValue(this.requestDto))
                .retrieve()
                .bodyToMono(InventoryResponseDto.class)
                .map(r -> r.getStatus().equals(InventoryStatus.AVAILABLE))
                .doOnNext(b -> this.stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
    }

    @Override
    public Mono<Boolean> revert() {
        return this.webClient
                .post()
                .uri("/inventory/add")
                .body(BodyInserters.fromValue(this.requestDto))
                .retrieve()
                .bodyToMono(Void.class)
                .map(r -> true)
                .onErrorReturn(false);
    }
}
