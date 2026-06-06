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
public class PaymentConsumer {
    private final Logger logger =  LoggerFactory.getLogger(PaymentConsumer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentConsumer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_IN_PROGRESS, groupId = "payment")
    public void handleOrderPayment(ConsumerRecord<String, OrderConfirmedEvent> record) {
        OrderConfirmedEvent orderConfirmedEvent = record.value();
        User user = orderConfirmedEvent.user();
        Order order = orderConfirmedEvent.order();
        Product product = order.getProduct();
        if (user.getUserWalletMaxLimit() > product.getProductValue()) {
            OrderPaymentCompleteEvent orderPaymentCompleteEvent =
                    new OrderPaymentCompleteEvent(order, user, "pay-"+order.getOrderId()+user.getUserId());
            kafkaTemplate.send(KafkaTopics.PAYMENT_COMPLETED, orderPaymentCompleteEvent);
        } else {
            OrderPaymentFailedEvent orderPaymentFailedEvent =
                    new OrderPaymentFailedEvent(order, user, "pay-"+order.getOrderId()+user.getUserId());
            kafkaTemplate.send(KafkaTopics.PAYMENT_FAILED, orderPaymentFailedEvent);
        }
    }
}
