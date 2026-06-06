package org.ecommerce.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Configuration
@EnableKafka
@RestController
public class ECommercePaymentMain {
    public static void main(String[] args) {
        SpringApplication.run(ECommercePaymentMain.class, args);
    }
}
