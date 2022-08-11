package ir.springboot.orchestrator.service;

import ir.springboot.common.dto.InventoryRequestDto;
import ir.springboot.common.dto.OrchestratorRequestDto;
import ir.springboot.common.dto.OrchestratorResponseDto;
import ir.springboot.common.dto.PaymentRequestDto;
import ir.springboot.common.enums.OrderStatus;
import ir.springboot.orchestrator.service.steps.InventoryStep;
import ir.springboot.orchestrator.service.steps.PaymentStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrchestratorService {

    @Autowired
    @Qualifier("payment")
    private WebClient paymentClient;

    @Autowired
    @Qualifier("inventory")
    private WebClient inventoryClient;

    public Mono<OrchestratorResponseDto> orderProduct(final OrchestratorRequestDto requestDto){
        Workflow orderWorkflow = this.getOrderWorkflow(requestDto);
        return Flux.fromStream(() -> orderWorkflow.getSteps().stream())
                .flatMap(WorkflowStep::process)
                .handle(((aBoolean, synchronousSink) -> {
                    if(aBoolean)
                        synchronousSink.next(true);
                    else
                        synchronousSink.error(new WorkflowException("create order failed!"));
                }))
                .then(Mono.fromCallable(() -> getResponseDTO(requestDto, OrderStatus.COMPLETE)))
                .onErrorResume(ex -> this.revertOrder(orderWorkflow, requestDto));
    }

    private Mono<OrchestratorResponseDto> revertOrder(final Workflow workflow, final OrchestratorRequestDto requestDTO){
        return Flux.fromStream(() -> workflow.getSteps().stream())
                .filter(wf -> wf.getStatus().equals(WorkflowStepStatus.COMPLETE))
                .flatMap(WorkflowStep::revert)
                .retry(3)
                .then(Mono.just(this.getResponseDTO(requestDTO, OrderStatus.CANCEL)));
    }

    private Workflow getOrderWorkflow(OrchestratorRequestDto requestDto){
        WorkflowStep paymentStep = new PaymentStep(this.paymentClient, this.getPaymentRequestDTO(requestDto));
        WorkflowStep inventoryStep = new InventoryStep(this.inventoryClient, this.getInventoryRequestDTO(requestDto));
        return new OrderWorkflow(List.of(paymentStep, inventoryStep));
    }

    private OrchestratorResponseDto getResponseDTO(OrchestratorRequestDto requestDto, OrderStatus status){
        OrchestratorResponseDto responseDTO = new OrchestratorResponseDto();
        responseDTO.setOrderId(requestDto.getOrderId());
        responseDTO.setAmount(requestDto.getAmount());
        responseDTO.setProductId(requestDto.getProductId());
        responseDTO.setUserId(requestDto.getUserId());
        responseDTO.setStatus(status);
        return responseDTO;
    }

    private PaymentRequestDto getPaymentRequestDTO(OrchestratorRequestDto requestDto){
        PaymentRequestDto paymentRequestDTO = new PaymentRequestDto();
        paymentRequestDTO.setUserId(requestDto.getUserId());
        paymentRequestDTO.setAmount(requestDto.getAmount());
        paymentRequestDTO.setOrderId(requestDto.getOrderId());
        return paymentRequestDTO;
    }

    private InventoryRequestDto getInventoryRequestDTO(OrchestratorRequestDto requestDto){
        InventoryRequestDto inventoryRequestDTO = new InventoryRequestDto();
        inventoryRequestDTO.setUserId(requestDto.getUserId());
        inventoryRequestDTO.setProductId(requestDto.getProductId());
        inventoryRequestDTO.setOrderId(requestDto.getOrderId());
        return inventoryRequestDTO;
    }
}
