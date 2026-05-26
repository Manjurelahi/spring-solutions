package circuitbreaker.resilience4j.controller;

import circuitbreaker.resilience4j.service.SpringRetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SpringRetryController {
    private final SpringRetryService springRetryService;

    public SpringRetryController(SpringRetryService springRetryService) {
        this.springRetryService = springRetryService;
    }

    @GetMapping("/testSpringRetry")
    public String callExternalServiceWithCircuitBreaker() {
        return springRetryService.callExternalServiceWithCircuitBreaker();
    }
}
