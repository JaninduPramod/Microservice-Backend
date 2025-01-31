package com.TravelBooking.safetravels.Repository;

import com.TravelBooking.safetravels.Model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

    @Query("SELECT b FROM BookingEntity b WHERE b.user_id = ?1")
    List<BookingEntity> findByUserId(int userId);


    @Transactional
    @Modifying
    @Query("UPDATE BookingEntity b SET b.booking_status = ?2 WHERE b.book_id = ?1")
    void updateBookingStatus(int bookId, String newStatus);

}
