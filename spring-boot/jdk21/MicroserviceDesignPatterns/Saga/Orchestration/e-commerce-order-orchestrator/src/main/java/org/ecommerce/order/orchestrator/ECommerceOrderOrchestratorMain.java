package org.ecommerce.order.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ECommerceOrderOrchestratorMain {
    public static void main(String[] args) {
        SpringApplication.run(ECommerceOrderOrchestratorMain.class, args);
    }
}
