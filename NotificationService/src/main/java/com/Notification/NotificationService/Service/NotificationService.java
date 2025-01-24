package com.Notification.NotificationService.Service;

import com.TravelBooking.safetravels.Model.BookingEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificationService {
    private final WebClient webClient;

    public NotificationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api/v4").build();
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
