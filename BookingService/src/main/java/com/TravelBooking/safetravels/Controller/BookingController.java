package com.TravelBooking.safetravels.Controller;

import com.TravelBooking.safetravels.Model.BookingEntity;
import com.TravelBooking.safetravels.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v4")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/getbookings")
    public List<BookingEntity> getBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/getbooking/{id}")
    public BookingEntity getBooking(@PathVariable int id){
        return bookingService.getBookingById(id);
    }

    @PostMapping("/newbooking")
    public ResponseEntity<String> addBooking(@RequestBody BookingEntity booking){
        String message=bookingService.SaveBooking(booking);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getbookingbyuserid/{userId}")
    public List<BookingEntity> getBookingByUserId(@PathVariable int userId){
        return bookingService.getBookingByUserId(userId);
    }

    @DeleteMapping("/deletebooking/{id}")
    public String deleteBooking(@PathVariable int id){
        return bookingService.DeleteBooking(id);
    }

    @PutMapping("/updatebooking/{id}")
    public BookingEntity updateBooking(@PathVariable int id, @RequestBody BookingEntity updatedBooking) {
       return bookingService.updateBooking(id ,updatedBooking);
    }


    @PutMapping("/bookingchange/{id}")
    public BookingEntity updateBookingStatus(@PathVariable int id,@RequestBody Map<String, String> requestBody){ {

        String status=requestBody.get("booking_status");
        return bookingService.updateBookingStatus(id,status);

    }

}
}
