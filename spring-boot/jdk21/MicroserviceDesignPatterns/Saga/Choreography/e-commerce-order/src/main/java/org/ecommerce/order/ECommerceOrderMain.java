package org.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ECommerceOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(ECommerceOrderMain.class, args);
    }
}
