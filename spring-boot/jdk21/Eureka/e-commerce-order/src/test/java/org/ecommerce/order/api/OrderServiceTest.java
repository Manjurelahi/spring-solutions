package org.ecommerce.order.api;

import org.ecommerce.order.model.Order;
import org.ecommerce.order.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    private final OrderService orderService;

    @Autowired
    public OrderServiceTest(OrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    public void testOrderDetails() {
        Order order = orderService.getOrderDetails(1);
        if (order != null) {
            Assertions.assertTrue(order.getOrderId() != 0);
            Product product = order.getProduct();
            Assertions.assertNotNull(product);
        }
    }

}
