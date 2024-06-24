package org.ecommerce.order.api;

import org.ecommerce.order.model.Order;
import org.ecommerce.order.model.Product;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testOrderDetails() {
        Order order = orderService.getOrderDetails(1);
        if (order != null) {
            Assertions.assertTrue(order.getOrderId() != 0);
            Product product = order.getProduct();
            Assertions.assertTrue(product != null);
        }
    }

}
