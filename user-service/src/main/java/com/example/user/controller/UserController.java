package com.example.user.controller;

import com.example.user.entity.DTO.UsersArrayList;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable("id") Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/nickname/{nickname}")
    public User findByNickname(@PathVariable String nickname) {
        return userService.findUserByNickname(nickname);
    }

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping("/usersArrayList")
    public UsersArrayList GetUsersDto() {
        ArrayList<User> users = (ArrayList<User>) userService.findAllUsers();
        UsersArrayList usersArrayList = new UsersArrayList(users);
        return usersArrayList;
    }
}
