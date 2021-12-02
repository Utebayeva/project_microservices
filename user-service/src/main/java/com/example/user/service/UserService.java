package com.example.user.service;

import com.example.user.entity.Log;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public List<User> findAllUsers() {
        LogRequest(1L, "GET", "Find all users");
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId) {
        LogRequest(1L, "GET", "Find user");
        return userRepository.findById(userId);
    }

    public void saveUser(User user) {
        LogRequest(1L, "POST", "Save user");
        userRepository.save(user);
    }

    public void updateUser(User user) {
        LogRequest(1L, "UPDATE", "Update user");
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        LogRequest(1L, "DELETE", "Delete user");
        userRepository.deleteById(userId);
    }

    private void LogRequest(Long userId, String action, String description) {
        amqpTemplate.convertAndSend("queue1", "Log from community-service");
        Log log = new Log(userId, "User-service", action, description);
        HttpEntity<Log> request = new HttpEntity<>(log);
        restTemplate.postForObject("http://logging-service/logs/saveLog", request, Log.class);
        System.out.println(log.toString());
    }
}
