package org.ecommerce.order.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ecommerce.common.constants.KafkaTopics;
import org.ecommerce.common.events.OrderPaymentFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentFailedConsumer {
    private Logger logger =  LoggerFactory.getLogger(OrderPaymentFailedConsumer.class);

    @KafkaListener(topics = KafkaTopics.PAYMENT_FAILED, groupId = "order")
    public void orderPaymentFailed(ConsumerRecord<String, OrderPaymentFailedEvent> record) {
        OrderPaymentFailedEvent orderPaymentFailedEvent = record.value();
        logger.info("Payment Failed: {}", orderPaymentFailedEvent);
    }
}
