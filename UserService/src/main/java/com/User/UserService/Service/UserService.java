package com.User.UserService.Service;

import com.User.UserService.Model.UserEntity;
import com.User.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    String OTP=null;


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserRepository userRepository;


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(int id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public void SaveUser(UserEntity user)
    {
        Optional<UserEntity> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            throw new RuntimeException("Email already exists! Please use a different email.");
        }
        userRepository.save(user);
    }

    public String DeleteUser(int id) {
        if(getUserById(id) != null)
        {
            userRepository.deleteById(id);
            return "User Removed ...";
        }
        else
        {
            return "User not found !";
        }
    }

    public UserEntity loginUser(String email, String password) {
        Optional<UserEntity> user = userRepository.findByEmail(email);

        if(user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }

        return null;
    }

    public void updateUser(UserEntity user) {

        userRepository.save(user);
    }

    public UserEntity update(UserEntity user) {

        return userRepository.save(user);
    }


    public UserEntity getUserByEmail(String email) {

        Optional<UserEntity> user = userRepository.findByEmail(email);

        return user.orElse(null);
    }










    public String generateOTP(String email) {
        UserEntity user = getUserByEmail(email);

        if(user==null) {
            return "unavailable";
        }
        else {

            String otp = UUID.randomUUID().toString().substring(0, 6);
            this.OTP = otp;
            sendEmail(email, "OTP for password reset", "Your OTP is: " + otp);


            return "Reset Password Sent to your email";
        }
    }









    public String processVerifyOTP(String OTP) {

        if(this.OTP.equals(OTP))
        {
            this.OTP = null;
            return "valid";
        }
        else
        {
            return "invalid";
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


}



