package com.TravelBooking.safetravels.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BookingWebClient {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    @Bean
    public WebClient notificationClient() {
        return WebClient.builder().baseUrl("http://localhost:8083").build();
    }
}
