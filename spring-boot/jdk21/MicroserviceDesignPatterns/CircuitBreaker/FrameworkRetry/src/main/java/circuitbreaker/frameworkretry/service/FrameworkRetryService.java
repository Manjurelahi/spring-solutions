package circuitbreaker.frameworkretry.service;

import circuitbreaker.frameworkretry.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FrameworkRetryService {
    private static final Logger logger = LoggerFactory.getLogger(FrameworkRetryService.class);
    private final RestClient restClient;
    private final CircuitBreakerFactory circuitBreakerFactory;
    int retryCount = 0;

    public FrameworkRetryService(RestClient restClient, CircuitBreakerFactory circuitBreakerFactory) {
        this.restClient = restClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public String getProductDetails() {
        String productDesc = "";
        //logger.info("Getting product details for product id : {}",1);
        try {
            Product product = restClient.get().uri("http://localhost:8081/products/1")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().body(Product.class);
            if (product != null) {
                retryCount = 0;
                productDesc = product.toString();
            }
        }  catch (Exception e) {
            logger.error("Error while getting product details for product id: {}",e.getMessage());
            logger.warn("Product API failed! Will retry. Retry Count: {}", ++retryCount);
            throw new RuntimeException(e);
        }
        return productDesc.equals("") ? "No Product Found" :  productDesc;
    }

   public String testFrameworkRetry() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("frameworkRetryService");
        return circuitBreaker.run(
                () -> getProductDetails(),
                throwable -> fallbackFrameworkRetry()
        );
    }

    public String fallbackFrameworkRetry() {
        retryCount = 0;
        return "Fallback Product";
    }
}
