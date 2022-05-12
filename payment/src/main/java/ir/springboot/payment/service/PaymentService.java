package ir.springboot.payment.service;

import ir.springboot.common.dto.PaymentRequestDto;
import ir.springboot.common.dto.PaymentResponseDto;
import ir.springboot.common.enums.PaymentStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private Map<Integer, Double> userBalanceMap;

    @PostConstruct
    private void init(){
        this.userBalanceMap = new HashMap<>();
        this.userBalanceMap.put(1, 1000d);
        this.userBalanceMap.put(2, 1000d);
        this.userBalanceMap.put(3, 1000d);
    }

    public Mono<PaymentResponseDto> debit(final PaymentRequestDto requestDTO){
        double balance = this.userBalanceMap.getOrDefault(requestDTO.getUserId(), 0d);
        PaymentResponseDto responseDTO = new PaymentResponseDto();
        responseDTO.setAmount(requestDTO.getAmount());
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setStatus(PaymentStatus.PAYMENT_REJECTED);
        if(balance >= requestDTO.getAmount()){
            responseDTO.setStatus(PaymentStatus.PAYMENT_APPROVED);
            this.userBalanceMap.put(requestDTO.getUserId(), balance - requestDTO.getAmount());
        }
        return Mono.just(responseDTO);
    }

    public Mono<String> credit(final PaymentRequestDto requestDTO){
        this.userBalanceMap.computeIfPresent(requestDTO.getUserId(), (k, v) -> v + requestDTO.getAmount());
        return Mono.empty();
    }

}
