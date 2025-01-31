package com.Notification.NotificationService.Controller;

import com.Notification.NotificationService.Service.NotificationService;
import com.TravelBooking.safetravels.Model.BookingEntity;
import com.User.UserService.Model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v5")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/booking-confirmed")
    public void bookingConfirmed(@RequestBody int bookingId) {
        notificationService.processBookingNotification(bookingId);
    }

    @GetMapping("/booking/{userId}")
    public List<BookingEntity> bookingByUserID(@PathVariable int userId) {
        return notificationService.processBookingByUserId(userId);
    }

    @GetMapping("/bookingstatus/{bookingId}")
    public String bookingStatus(@PathVariable int bookingId) {
        return notificationService.processBookingStatus(bookingId);
    }

    @DeleteMapping("/bookingstatus/{bookingId}")
    public String Deletebooking(@PathVariable int bookingId) {
        return notificationService.DeleteBookingStatus(bookingId);
    }

    @PutMapping("/updatestatus/{bookingId}")
    public String Updatebooking(@PathVariable int bookingId,BookingEntity updatedBooking) {
        return notificationService.UpdateBookingStatus(bookingId);
    }
//    @GetMapping("/users/{userId}")
//    public List<UserEntity> fetchAllUsers(@PathVariable int userId) {
//        return notificationService.processUserNotification(userId);
//    }


}
