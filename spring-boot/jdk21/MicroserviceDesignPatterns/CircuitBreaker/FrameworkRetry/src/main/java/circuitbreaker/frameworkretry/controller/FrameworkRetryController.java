package circuitbreaker.frameworkretry.controller;

import circuitbreaker.frameworkretry.service.FrameworkRetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FrameworkRetryController {
    private final FrameworkRetryService frameworkRetryService;

    public FrameworkRetryController(FrameworkRetryService frameworkRetryService) {
        this.frameworkRetryService = frameworkRetryService;
    }

    @GetMapping("/testFrameworkRetry")
    public String testFrameworkRetry() {
        return frameworkRetryService.testFrameworkRetry();
    }
}
