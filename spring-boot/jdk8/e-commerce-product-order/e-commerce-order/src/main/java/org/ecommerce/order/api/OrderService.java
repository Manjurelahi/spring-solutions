package org.ecommerce.order.api;

import org.ecommerce.order.model.Order;
import org.ecommerce.order.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderService {
    @Value("${ecommerce.product.url}")
    private String API_URL;

    public Order getOrderDetails(Integer productId) {
        WebClient webClient = WebClient.builder().baseUrl(API_URL + "/" + productId).build();
        Product product = webClient.get().retrieve().bodyToMono(Product.class).block();
        Order order = new Order(1, product);
        return order;
    }
}