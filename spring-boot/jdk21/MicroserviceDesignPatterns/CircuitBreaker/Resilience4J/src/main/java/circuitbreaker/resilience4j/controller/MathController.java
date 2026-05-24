package circuitbreaker.resilience4j.controller;

import circuitbreaker.resilience4j.service.MathService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MathController {
    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/testResilience4J")
    public String callExternalServiceWithCircuitBreaker() {
        return mathService.callExternalServiceWithCircuitBreaker();
    }
}
