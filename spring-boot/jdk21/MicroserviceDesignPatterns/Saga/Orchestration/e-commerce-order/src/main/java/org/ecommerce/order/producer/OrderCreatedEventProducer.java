package org.ecommerce.order.producer;

import org.ecommerce.common.constants.KafkaTopics;
import org.ecommerce.common.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventProducer {
    private static final Logger log = LoggerFactory.getLogger(OrderCreatedEventProducer.class.getName());
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    public OrderCreatedEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendOrderCreatedEvent(OrderCreatedEvent  orderCreatedEvent) {
        kafkaTemplate.send(KafkaTopics.ORDER_CREATED, orderCreatedEvent);
        log.info("Published order created event: {}", orderCreatedEvent);
    }
}
