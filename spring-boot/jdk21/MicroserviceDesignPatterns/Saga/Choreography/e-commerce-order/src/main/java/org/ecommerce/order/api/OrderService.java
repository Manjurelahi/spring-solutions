package org.ecommerce.order.api;

import org.ecommerce.common.constants.KafkaTopics;
import org.ecommerce.common.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderService {


    private static final Logger log =
            LoggerFactory.getLogger(OrderService.class.getName());

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public OrderService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String getOrderDetails(Integer productId) {
        Integer orderId = new Random().nextInt(1, 11);
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(orderId, "TestUser", productId);
        kafkaTemplate.send(KafkaTopics.ORDER_CREATED, orderCreatedEvent);
        log.info("Published order created event: {}", orderCreatedEvent);
        return "Order created.";
    }
}