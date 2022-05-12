package ir.springboot.payment;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@ComponentScan("ir.springboot.payment")
public class PaymentApplicationTest
{
    @Test
    void contextLoads() {
    }
}
