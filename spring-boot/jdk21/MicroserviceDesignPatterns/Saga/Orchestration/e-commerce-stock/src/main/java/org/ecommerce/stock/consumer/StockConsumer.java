package org.ecommerce.stock.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ecommerce.common.constants.KafkaTopics;
import org.ecommerce.common.events.OrderConfirmedEvent;
import org.ecommerce.common.events.OrderCreatedEvent;
import org.ecommerce.common.events.OrderOutOfStockEvent;
import org.ecommerce.common.model.Order;
import org.ecommerce.common.model.Product;
import org.ecommerce.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StockConsumer {
    private final Logger logger =  LoggerFactory.getLogger(StockConsumer.class);
    private final WebClient webClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public StockConsumer(WebClient webClient, KafkaTemplate<String, Object> kafkaTemplate) {
        this.webClient = webClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = KafkaTopics.ORDER_IN_PROGRESS, groupId = "stock")
    public void orderInProgress(ConsumerRecord<String, OrderCreatedEvent> record) {
        OrderCreatedEvent orderCreatedEvent = record.value();
        logger.info("Received OrderCreatedEvent: {}", orderCreatedEvent);
        Integer productId = orderCreatedEvent.productId();
        webClient.get().uri("/"+productId)
                .retrieve().bodyToMono(Product.class).subscribe(
                        product ->  {
                            logger.info("Received Product: {}", product);
                            if (product.getIsInStock()) {
                                OrderConfirmedEvent orderConfirmedEvent = new OrderConfirmedEvent(
                                        new Order(orderCreatedEvent.orderId(), product),
                                        new User(orderCreatedEvent.userId(), 15000)
                                );
                                kafkaTemplate.send(KafkaTopics.ORDER_CONFIRMED, orderConfirmedEvent);
                            } else {
                                OrderOutOfStockEvent orderOutOfStockEvent = new OrderOutOfStockEvent(
                                        orderCreatedEvent.orderId(),
                                        orderCreatedEvent.productId()
                                );
                                kafkaTemplate.send(KafkaTopics.ORDER_OUT_OF_STOCK, orderOutOfStockEvent);
                            }
                        },
                        throwable -> logger.error("Error getting Product", throwable)
                );
    }
}
