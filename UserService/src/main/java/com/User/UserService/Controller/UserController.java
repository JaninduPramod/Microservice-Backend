package com.User.UserService.Controller;

import com.User.UserService.Model.UserEntity;
import com.User.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<UserEntity> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getuser/{id}")
    public UserEntity getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/newuser")
    public void addUser(@RequestBody UserEntity user) {
        userService.SaveUser(user);
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.DeleteUser(id);
    }


}
