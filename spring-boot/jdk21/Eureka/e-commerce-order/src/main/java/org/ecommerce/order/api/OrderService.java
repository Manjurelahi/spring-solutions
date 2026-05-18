package org.ecommerce.order.api;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.ecommerce.order.model.Order;
import org.ecommerce.order.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Service
public class OrderService {
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private static final String PRODUCT_SERVICE = "product-service";

    @Value("${spring.webflux.webclient}")
    private boolean isWebClient;

    public OrderService(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        this.restClient = restClientBuilder.build();
    }

    public Order getOrderDetails(Integer productId) {
        Product product = null;
        ServiceInstance serviceInstance = discoveryClient.getInstances("product-service").get(0);
        if (isWebClient) {
            WebClient webClient = WebClient.builder()
                    .baseUrl(serviceInstance.getUri().toString())
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .clientConnector(new ReactorClientHttpConnector(
                            HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE)
                    ))
                    .build();
            product = webClient.get()
                    .uri("/products/" + productId)
                    .retrieve().bodyToMono(Product.class).block();
        } else {
            product = restClient.get()
                    .uri(serviceInstance.getUri() + "/products/" + productId)
                    .retrieve().body(Product.class);
        }
        return new Order(1, product);
    }
}