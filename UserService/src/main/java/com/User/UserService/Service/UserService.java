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


}
