package com.Notification.NotificationService.Controller;

import com.Notification.NotificationService.Service.NotificationService;
import com.TravelBooking.safetravels.Model.BookingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v5")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/booking-confirmed")
    public void bookingConfirmed(@RequestBody int bookingId) {
        notificationService.processBookingNotification(bookingId);
    }


}
