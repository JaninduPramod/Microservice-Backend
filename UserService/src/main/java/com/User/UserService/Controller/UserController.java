package com.User.UserService.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
}
