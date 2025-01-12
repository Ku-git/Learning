package com.ku.controller;

import com.ku.entity.User;
import com.ku.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @PostMapping
//    public String createUser(@RequestBody User user) {
//        userService.createUser(user);
//        return "User created successfully";
//    }
//
//    @PutMapping
//    public String updateUser(@RequestBody User user) {
//        userService.updateUser(user);
//        return "User updated successfully";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return "User deleted successfully";
//    }
}

