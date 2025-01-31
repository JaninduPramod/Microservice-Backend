package com.Notification.NotificationService.Modle;

import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notify_id;

    private int user_id;
    private String user_email;
    private int book_id;
    private int hotel_id;
    private int package_id;
    private int no_of_days;
    private int no_of_packages;
    private int total_bill;

    public int getTotal_bill() {
        return total_bill;
    }

    public void setTotal_bill(int total_bill) {
        this.total_bill = total_bill;
    }

    public int getNo_of_packages() {
        return no_of_packages;
    }

    public void setNo_of_packages(int no_of_packages) {
        this.no_of_packages = no_of_packages;
    }

    public int getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(int no_of_days) {
        this.no_of_days = no_of_days;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(int notify_id) {
        this.notify_id = notify_id;
    }
}
