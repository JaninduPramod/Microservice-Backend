package com.Notification.NotificationService.Service;

import com.TravelBooking.safetravels.Model.BookingEntity;
import com.User.UserService.Model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class NotificationService {
    private final WebClient webClient;
    private final WebClient UserClient;


    @Autowired
    public NotificationService(WebClient.Builder webClientBuilder, WebClient.Builder userClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api/v4").build();
        this.UserClient = userClientBuilder.baseUrl("http://localhost:8082/api/v3").build();

    }

    public void processBookingNotification(int bookingId) {
        BookingEntity booking = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getbooking/{id}").build(bookingId))
                .retrieve()
                .bodyToMono(BookingEntity.class)
                .block();

        System.out.println("Booking Status : "+booking.getBooking_status());

    }

}
