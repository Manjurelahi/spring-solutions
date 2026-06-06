package org.ecommerce.order.api;

import org.ecommerce.common.events.OrderCreatedEvent;
import org.ecommerce.order.producer.OrderCreatedEventProducer;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderService {
    private final OrderCreatedEventProducer orderCreatedEventProducer;

    public OrderService(OrderCreatedEventProducer orderCreatedEventProducer) {
        this.orderCreatedEventProducer = orderCreatedEventProducer;
    }

    public String getOrderDetails(Integer productId) {
        Integer orderId = new Random().nextInt(1, 11);
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(orderId, "TestUser", productId);
        orderCreatedEventProducer.sendOrderCreatedEvent(orderCreatedEvent);
        return "Order created.";
    }
}