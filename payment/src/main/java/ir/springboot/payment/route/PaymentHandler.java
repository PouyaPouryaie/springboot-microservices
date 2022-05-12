package ir.springboot.payment.route;

import ir.springboot.common.dto.InventoryRequestDto;
import ir.springboot.common.dto.InventoryResponseDto;
import ir.springboot.common.dto.PaymentRequestDto;
import ir.springboot.common.dto.PaymentResponseDto;
import ir.springboot.common.utils.ResponseUtils;
import ir.springboot.payment.service.PaymentService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PaymentHandler {

    private final PaymentService service;

    public PaymentHandler(PaymentService paymentService) {
        this.service = paymentService;
    }

    public Mono<ServerResponse> debit(ServerRequest request){
        return request.bodyToMono(PaymentRequestDto.class)
                .flatMap(service::debit)
                .flatMap(paymentResponseDto -> ResponseUtils.ok(paymentResponseDto, PaymentResponseDto.class));
    }

    public Mono<ServerResponse> credit(ServerRequest request) {
        return request.bodyToMono(PaymentRequestDto.class)
                .flatMap(service::credit)
                .flatMap(ignore -> ResponseUtils.ok());
    }

}
