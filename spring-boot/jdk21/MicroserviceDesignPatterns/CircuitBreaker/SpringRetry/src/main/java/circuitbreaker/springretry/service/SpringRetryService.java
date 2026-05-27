package circuitbreaker.springretry.service;

import circuitbreaker.springretry.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class SpringRetryService {
    private static final Logger logger = LoggerFactory.getLogger(SpringRetryService.class);
    private final RestClient restClient;

    @Autowired
    public SpringRetryService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Retryable(retryFor = { Throwable.class }, maxAttempts = 5, backoff = @Backoff(delay = 2000))
    public String callExternalServiceWithCircuitBreaker() {
        int retryCount = RetrySynchronizationManager.getContext().getRetryCount();
        System.out.println("Current retry attempt: " + retryCount);
        return restClient.get().uri("http://localhost:8081/products/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve().body(Product.class).toString();
    }

    @Recover
    public String fallbackMethodResponse(Throwable error) {
        logger.error("Fallback Method Exception", error);
        return "Recovered from - "+error.getMessage();
    }
}
