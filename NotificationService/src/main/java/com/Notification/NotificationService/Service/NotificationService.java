package com.Notification.NotificationService.Service;

import com.Notification.NotificationService.Modle.NotificationEntity;
import com.Notification.NotificationService.Modle.NotificationRepository;
import com.TravelBooking.safetravels.Model.BookingEntity;
import com.User.UserService.Model.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class NotificationService {
    private final WebClient webClient;
    private final WebClient UserClient;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(WebClient.Builder webClientBuilder, WebClient.Builder userClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api/v4").build();
        this.UserClient = userClientBuilder.baseUrl("http://localhost:8082/api/v3").build();

    }

    public void processBookingNotification(int bookingId) {
        BookingEntity booking = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getbooking/{id}").build(bookingId))
                .retrieve()
                .bodyToMono(BookingEntity.class)
                .block();

        System.out.println("Booking Status : "+booking.getBooking_status());

    }

    public UserEntity processUserDetails(int userId) {
        try
        {
            UserEntity user = UserClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getuser/{id}").build(userId))
                    .retrieve()
                    .bodyToMono(UserEntity.class)
                    .block();


            return user;
        }
        catch (Exception e)
        {
            System.out.println("Something went Wrong !!!");
        }
        return null;

    }

    public List<BookingEntity> processBookingByUserId(int userId) {
        try
        {
            List<BookingEntity> booking = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getbookingbyuserid/{userId}").build(userId))
                    .retrieve()
                    .bodyToFlux(BookingEntity.class)
                    .collectList()
                    .block();

            return booking;


        }
        catch (Exception e)
        {
            System.out.println("Something went Wrong !!!");
        }

        return null;

    }

    @Transactional
    public String sendingBookingNotification(int bookingId) {
        String response = null;
        try
        {
            BookingEntity booking = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getbooking/{id}").build(bookingId))
                    .retrieve()
                    .bodyToMono(BookingEntity.class)
                    .block();


            UserEntity user = processUserDetails(booking.getUser_id());
            String email=user.getEmail();
            System.out.println("User Email : "+email);

            
            if ("cancelled".equalsIgnoreCase(booking.getBooking_status())) {
                response = "Booking Cancelled !!!";
                sendEmail(email, "Booking Cancelled", "Your booking (ID: " + bookingId + ") has been cancelled.");




                System.out.println("Email sent to: " + email);
            } else if ("payed".equalsIgnoreCase(booking.getBooking_status())) {
                response = "Booking Payed ...";
                sendEmail(email, "Booking Payment Successful", "Your booking (ID: " + bookingId + ") has been successfully paid.");
                System.out.println("Email sent to: " + email);

                NotificationEntity notificationEntity = new NotificationEntity();
                notificationEntity.setUser_id(booking.getUser_id());
                notificationEntity.setUser_email(email);
                notificationEntity.setBook_id(booking.getBook_id());
                notificationEntity.setHotel_id(booking.getHotel_id());
                notificationEntity.setPackage_id(booking.getPackage_id());
                notificationEntity.setNo_of_days(booking.getNo_of_days());
                notificationEntity.setNo_of_packages(booking.getNo_of_packages());
                notificationEntity.setTotal_bill(booking.getTotal_bill());


                notificationRepository.save(notificationEntity);
            }


            return response;


        }
        catch (Exception e)
        {
            return "Cannot fetch Booking Data !!!";
        }


    }


    private void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }


    public String DeleteBookingStatus(int bookingId) {
        try {
            webClient.delete()
                    .uri(uriBuilder -> uriBuilder.path("/deletebooking/{id}").build(bookingId))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();

            return "Booking deleted successfully!";
        } catch (Exception e) {
            return "Something went wrong while deleting the booking!";
        }
    }
    public String UpdateBookingStatus(int bookingId) {
        try {
            BookingEntity booking = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getbooking/{id}").build(bookingId))
                    .retrieve()
                    .bodyToMono(BookingEntity.class)
                    .block();

            if (booking != null) {
                // Update the booking status (example: setting it to "Confirmed")
                booking.setBooking_status("Confirmed");

                // Send PUT request to update the booking in the backend
                webClient.put()
                        .uri(uriBuilder -> uriBuilder.path("/updatebooking/{id}").build(bookingId))
                        .bodyValue(booking)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();

                return "Booking status updated successfully!";
            } else {
                return "Booking not found!";
            }
        } catch (Exception e) {
            return "Error updating booking status: " + e.getMessage();
        }
    }


    //crud tika
    public List<NotificationEntity> allInvoices() {

        List<NotificationEntity> invoices = notificationRepository.findAll();
        return invoices;
    }

    public NotificationEntity notificationById(int id) {
        NotificationEntity invoice = notificationRepository.findById(id).orElse(null);
        return invoice;
    }

    public String deleteInvoice(int id) {

            notificationRepository.deleteById(id);
            return "Invoice deleted successfully!";
    }
}
