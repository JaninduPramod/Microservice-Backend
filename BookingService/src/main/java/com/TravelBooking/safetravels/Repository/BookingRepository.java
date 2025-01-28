package com.TravelBooking.safetravels.Repository;

import com.TravelBooking.safetravels.Model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {


}
