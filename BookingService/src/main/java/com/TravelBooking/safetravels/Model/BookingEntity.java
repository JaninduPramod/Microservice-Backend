package com.TravelBooking.safetravels.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    private int user_id;

    private int hotel_id;

    private int package_id;

    private String booking_status;

    private int no_of_days;

    private int no_of_packages;

    private int total_bill;


}