package com.TravelBooking.safetravels.Service;

import HotelService.Model.HotelEntity;
import com.TravelBooking.safetravels.Model.BookingEntity;
import com.TravelBooking.safetravels.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final WebClient webClient;

    @Autowired
    private BookingRepository bookingRepository;

    public BookingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/v2").build();
    }

    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingEntity getBookingById(int id) {
        Optional<BookingEntity> booking = bookingRepository.findById(id);
        return booking.orElse(null);
    }

    public void SaveBooking(BookingEntity booking)
    {
        Integer hotelID = booking.getHotel_id();
        Integer days = booking.getNo_of_days();


        try
        {
            HotelEntity hotelResponse= webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/gethotel/{id}").build(hotelID))
                    .retrieve()
                    .bodyToMono(HotelEntity.class)
                    .block();

//            System.out.println(hotelResponse.getPrice_perday());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


//        bookingRepository.save(booking);
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
