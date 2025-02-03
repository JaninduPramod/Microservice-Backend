package com.User.UserService.Service;

import com.User.UserService.Model.UserEntity;
import com.User.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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
            throw new RuntimeException("Email already exists. Please use a different email.");
        }
        userRepository.save(user);
    }

    public String DeleteUser(int id) {
        if(getUserById(id) != null)
        {
            userRepository.deleteById(id);
            return "User deleted ...";
        }
        else
        {
            return "User not found !!!";
        }
    }

    public UserEntity loginUser(String email, String password) {
        Optional<UserEntity> user = userRepository.findByEmail(email);

        if(user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }

        return null;
    }


    public UserEntity findUserByEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
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



}



