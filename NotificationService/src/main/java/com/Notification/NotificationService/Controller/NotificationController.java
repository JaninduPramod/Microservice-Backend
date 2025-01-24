package com.Notification.NotificationService.Controller;

import com.Notification.NotificationService.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
