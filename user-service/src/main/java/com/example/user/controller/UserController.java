package com.example.user.controller;

import com.example.user.VO.ResponseTemplate;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") Long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/findUserGames/{id}")
    public ResponseTemplate findUserGames(@PathVariable("id") Long userId) {
        return userService.findUserGames(userId);
    }
}
