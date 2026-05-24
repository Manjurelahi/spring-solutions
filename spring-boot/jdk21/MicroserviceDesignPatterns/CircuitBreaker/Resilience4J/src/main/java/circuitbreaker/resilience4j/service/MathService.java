package circuitbreaker.resilience4j.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class MathService {
    private static final Logger LOG = LoggerFactory.getLogger(MathService.class);
    private CircuitBreakerFactory cbFactory;

    public MathService(CircuitBreakerFactory cbFactory) {
        this.cbFactory = cbFactory;
    }

    public String callExternalServiceWithCircuitBreaker() {
        return cbFactory.create("mathService").run(
                //Call here external API method instead of below dummy method
                ()-> mathDummyMethod(),
                throwable -> fallbackMethodResponse(throwable)
        );
    }

    public String mathDummyMethod() throws RuntimeException {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Service Failed");
        }
        return "Service Success";
    }

    public String fallbackMethodResponse(Throwable error) {
        LOG.error("Error while executing math dummy method", error);
        return "Fallback response: " + error.getMessage();
    }
}
