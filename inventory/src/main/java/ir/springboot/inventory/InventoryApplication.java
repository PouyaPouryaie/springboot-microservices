package ir.springboot.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class InventoryApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(InventoryApplication.class, args);
    }
}
