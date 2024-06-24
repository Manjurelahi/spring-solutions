package org.ecommerce.order.api;

import org.ecommerce.order.model.Order;
import org.ecommerce.order.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders/createOrder")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> getOrderDetails(@RequestBody Product product) {
        return new ResponseEntity<>(orderService.getOrderDetails(product.getProductId()), HttpStatus.OK);
    }
}