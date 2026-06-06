package org.ecommerce.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ECommercePaymentMain {
    public static void main(String[] args) {
        SpringApplication.run(ECommercePaymentMain.class, args);
    }
}
