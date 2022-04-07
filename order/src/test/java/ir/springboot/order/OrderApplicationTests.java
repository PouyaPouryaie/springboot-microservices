package ir.springboot.order;

import ir.springboot.order.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@ComponentScan("ir.micro.order")
class OrderApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	void getRequest(){
		webTestClient.get().uri("/order/hello").accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Order.class);
	}
}
