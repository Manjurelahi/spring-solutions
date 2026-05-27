package circuitbreaker.frameworkretry.config;

import org.springframework.cloud.circuitbreaker.retry.FrameworkRetryCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.retry.FrameworkRetryConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
@EnableResilientMethods
public class FrameworkRetryConfig {
    @Bean
    public RestClient  restClient() {
        return RestClient.builder().build();
    }

    @Bean
    public Customizer<FrameworkRetryCircuitBreakerFactory> customRetryPolicy() {
        return factory -> factory.configureDefault(id ->
                new FrameworkRetryConfigBuilder(id)
                        .retryPolicy(RetryPolicy.builder()
                        .maxRetries(5)
                        .delay(Duration.ofSeconds(2))
                        .timeout(Duration.ofMinutes(1))
                        .build())
                .build());
    }
}
