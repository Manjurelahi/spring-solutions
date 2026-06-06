package org.ecommerce.payment.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ecommerce.common.constants.KafkaTopics;
import org.ecommerce.common.events.OrderConfirmedEvent;
import org.ecommerce.common.events.OrderPaymentCompleteEvent;
import org.ecommerce.common.events.OrderPaymentFailedEvent;
import org.ecommerce.common.model.Order;
import org.ecommerce.common.model.Product;
import org.ecommerce.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderConfirmedEventConsumer {
    private Logger logger =  LoggerFactory.getLogger(OrderConfirmedEventConsumer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderConfirmedEventConsumer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = KafkaTopics.ORDER_CONFIRMED, groupId = "payment")
    public void orderConfirmed(ConsumerRecord<String, OrderConfirmedEvent> orderConfirmedEventRecord) {
        OrderConfirmedEvent orderConfirmedEvent = orderConfirmedEventRecord.value();
        User user = orderConfirmedEvent.user();
        Order order = orderConfirmedEvent.order();
        Product product = order.getProduct();
        if (user.getUserWalletMaxLimit() > product.getProductValue()) {
            OrderPaymentCompleteEvent orderPaymentCompleteEvent =
                    new OrderPaymentCompleteEvent(order, user, "pay-"+order.getOrderId()+user.getUserId());
            logger.info("Payment Completed: {}", orderPaymentCompleteEvent);
        } else {
            OrderPaymentFailedEvent orderPaymentFailedEvent =
                    new OrderPaymentFailedEvent(order, user, "pay-"+order.getOrderId()+user.getUserId());
            logger.info("Payment Failed due to max user wallet limit: {}", orderPaymentFailedEvent);
            kafkaTemplate.send(KafkaTopics.PAYMENT_FAILED, orderPaymentFailedEvent);
        }
    }
}
