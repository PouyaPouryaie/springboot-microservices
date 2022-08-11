package ir.springboot.orchestrator.service.steps;

import ir.springboot.common.dto.PaymentRequestDto;
import ir.springboot.common.dto.PaymentResponseDto;
import ir.springboot.common.enums.PaymentStatus;
import ir.springboot.orchestrator.service.WorkflowStep;
import ir.springboot.orchestrator.service.WorkflowStepStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class PaymentStep implements WorkflowStep {

    private final WebClient webClient;
    private final PaymentRequestDto requestDto;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public PaymentStep(WebClient webClient, PaymentRequestDto requestDto) {
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
                .uri("/payment/debit")
                .body(BodyInserters.fromValue(this.requestDto))
                .retrieve()
                .bodyToMono(PaymentResponseDto.class)
                .map(r -> r.getStatus().equals(PaymentStatus.PAYMENT_APPROVED))
                .doOnNext(b -> this.stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
    }

    @Override
    public Mono<Boolean> revert() {
        return this.webClient
                .post()
                .uri("/payment/credit")
                .body(BodyInserters.fromValue(this.requestDto))
                .retrieve()
                .bodyToMono(Void.class)
                .map(r -> true)
                .onErrorReturn(false);
    }
}
