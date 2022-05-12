package ir.springboot.payment.route;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Service
public class PaymentRouter {

    private final PaymentHandler paymentHandler;

    public PaymentRouter(PaymentHandler paymentHandler) {
        this.paymentHandler = paymentHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> paymentRouterFunction(){
        return nest(path("/payment"),
                route(POST("/debit")
                        .and(accept(MediaType.APPLICATION_JSON))
                        .and(contentType(MediaType.APPLICATION_JSON)), paymentHandler::debit)
                        .andRoute(POST("/credit")
                                .and(accept(MediaType.APPLICATION_JSON))
                                .and(contentType(MediaType.APPLICATION_JSON)), paymentHandler::credit));
    }
}
