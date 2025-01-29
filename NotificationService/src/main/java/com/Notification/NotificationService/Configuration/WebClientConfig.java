package com.Notification.NotificationService.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient bookingClient() {
        return WebClient.builder().baseUrl("http://localhost:8081").build();
    }

    @Bean
    public WebClient userClient() {
        return WebClient.builder().baseUrl("http://localhost:8082").build();
    }
}
