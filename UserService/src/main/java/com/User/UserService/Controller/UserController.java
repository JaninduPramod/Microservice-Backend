package com.User.UserService.Controller;

import com.User.UserService.Model.UserEntity;
import com.User.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v3")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<UserEntity> getAllUsers() {
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

    @PostMapping("/login")
    public String loginUser(@RequestBody UserEntity user) {
        UserEntity foundUser = userService.loginUser(user.getEmail(), user.getPassword());

        if (foundUser != null) {
            return "Login successful";
        }
        else {
            return "Invalid email or password";
        }
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        UserEntity user = userService.findUserByEmail(email);

        if (user == null) {
            return "User not found!";
        }

        String tempPassword = UUID.randomUUID().toString().substring(0, 8);
        user.setPassword(tempPassword);
        userService.updateUser(user);


        return "A temporary password has been sent to your email.";
    }

    @GetMapping("/getuserbyemail/{email}")
    public UserEntity getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/update")
    public UserEntity updateUser(@RequestBody UserEntity user) {
        return userService.update(user);
    }



    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e) {
        return e.getMessage();
    }



}






