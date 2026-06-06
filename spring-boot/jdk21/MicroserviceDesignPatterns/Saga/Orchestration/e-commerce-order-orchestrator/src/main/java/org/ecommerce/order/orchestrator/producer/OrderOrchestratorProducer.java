package org.ecommerce.order.orchestrator.producer;

import org.ecommerce.common.constants.KafkaTopics;
import org.ecommerce.common.events.OrderConfirmedEvent;
import org.ecommerce.common.events.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class OrderOrchestratorProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderOrchestratorProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void checkStock(OrderCreatedEvent orderCreatedEvent) {
        kafkaTemplate.send(KafkaTopics.ORDER_IN_PROGRESS,  orderCreatedEvent);
    }

    public void checkOrderPayment(OrderConfirmedEvent orderConfirmedEvent) {
        kafkaTemplate.send(KafkaTopics.PAYMENT_IN_PROGRESS, orderConfirmedEvent);
    }
}
