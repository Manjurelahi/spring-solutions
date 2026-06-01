package org.ecommerce.order.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ecommerce.common.constants.KafkaTopics;
import org.ecommerce.common.events.OrderOutOfStockEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderOutOfStockConsumer {
    private Logger logger =  LoggerFactory.getLogger(OrderOutOfStockConsumer.class);

    @KafkaListener(topics = KafkaTopics.ORDER_OUT_OF_STOCK, groupId = "order")
    public void orderOutOfStock(ConsumerRecord<String, OrderOutOfStockEvent> record) {
        OrderOutOfStockEvent orderOutOfStockEvent = record.value();
        logger.info("Received Order Out Of Stock Event: {}", orderOutOfStockEvent);
    }
}
