package circuitbreaker.resilience4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestClient;

@Configuration
@EnableRetry
public class SpringRetryConfig {
    @Bean
    public RestClient  restClient() {
        return RestClient.builder().build();
    }
}
