package com.TravelBooking.safetravels.Service;

import com.TravelBooking.safetravels.Model.BookingEntity;
import com.TravelBooking.safetravels.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingEntity getBookingById(int id) {
        Optional<BookingEntity> booking = bookingRepository.findById(id);
        return booking.orElse(null);
    }

    public void SaveBooking(BookingEntity booking)
    {
        bookingRepository.save(booking);
    }

    public String DeleteBooking(int id) {
        if(getBookingById(id) != null)
        {
            bookingRepository.deleteById(id);
            return "Booking deleted ...";
        }
        else
        {
            return "Booking not found !!!";
        }
    }


}
