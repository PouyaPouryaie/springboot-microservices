package ir.springboot.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CircuitBreakerController {

    @GetMapping("/defaultFallback")
    public Mono<String> defaultMessage(){
        return Mono.just("There are some error in connecting, please try later");
    }
}
