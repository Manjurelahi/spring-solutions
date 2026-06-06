package org.ecommerce.order.orchestrator.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ecommerce.common.constants.KafkaTopics;
import org.ecommerce.common.events.OrderConfirmedEvent;
import org.ecommerce.common.events.OrderCreatedEvent;
import org.ecommerce.common.events.OrderOutOfStockEvent;
import org.ecommerce.common.events.OrderPaymentCompleteEvent;
import org.ecommerce.common.events.OrderPaymentFailedEvent;
import org.ecommerce.order.orchestrator.producer.OrderOrchestratorProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderOrchestratorConsumer {
    private final Logger logger = LoggerFactory.getLogger(OrderOrchestratorConsumer.class);
    private final OrderOrchestratorProducer  orderOrchestratorProducer;

    public OrderOrchestratorConsumer(OrderOrchestratorProducer orderOrchestratorProducer) {
        this.orderOrchestratorProducer = orderOrchestratorProducer;
    }

    @KafkaListener(topics = KafkaTopics.ORDER_CREATED, groupId = "order-orchestrator")
    public void checkStock(ConsumerRecord<String, OrderCreatedEvent> record) {
        OrderCreatedEvent orderCreatedEvent = record.value();
        orderOrchestratorProducer.checkStock(orderCreatedEvent);
    }

    @KafkaListener(topics = KafkaTopics.ORDER_CONFIRMED, groupId = "order-orchestrator")
    public void checkOrderPayment(ConsumerRecord<String, OrderConfirmedEvent> record) {
        OrderConfirmedEvent orderConfirmedEvent = record.value();
        orderOrchestratorProducer.checkOrderPayment(orderConfirmedEvent);
    }

    @KafkaListener(topics = KafkaTopics.ORDER_OUT_OF_STOCK, groupId = "order-orchestrator")
    public void orderOutOfStock(ConsumerRecord<String, OrderOutOfStockEvent> record) {
        OrderOutOfStockEvent orderOutOfStockEvent = record.value();
        logger.info("Received Order Out Of Stock Event: {}", orderOutOfStockEvent);
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_COMPLETED, groupId = "order-orchestrator")
    public void paymentComplete(ConsumerRecord<String, OrderPaymentCompleteEvent> record) {
        OrderPaymentCompleteEvent orderPaymentCompleteEvent = record.value();
        logger.info("Payment Completed: {}", orderPaymentCompleteEvent);
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_FAILED, groupId = "order-orchestrator")
    public void paymentFailed(ConsumerRecord<String, OrderPaymentFailedEvent> record) {
        OrderPaymentFailedEvent orderPaymentFailedEvent = record.value();
        logger.info("Payment Failed due to max user wallet limit: {}", orderPaymentFailedEvent);
    }
}
