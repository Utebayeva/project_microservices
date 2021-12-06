package com.example.community.service;

import com.example.community.entity.Community;
import com.example.community.entity.DTO.UserCommunity;
import com.example.community.entity.Game;
import com.example.community.entity.Log;
import com.example.community.entity.User;
import com.example.community.repository.CommunityRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public List<Community> findAllCommunities() {
        LogRequest(1L, "GET", "Find all communities");
        return communityRepository.findAll();
    }

    public List<Community> findCommunityByGameId(Long gameId) {
        LogRequest(1L, "GET", "Find community");
        return communityRepository.findCommunityByGameId(gameId);
    }

    public void saveCommunity(Community community) {
        User user = restTemplate.getForObject("http://user-service/users/" + Long.toString(community.getUserId()), User.class);
        Game game = restTemplate.getForObject("http://game-service/games/" + Long.toString(community.getGameId()), Game.class);
        if (user != null && game != null) {
            communityRepository.save(community);
            LogRequest(1L, "POST", "Save community");
        } else {
            LogRequest(1L, "POST", "Not saved community");
        }
    }

    public void updateCommunity(Community community) {
        LogRequest(1L, "PUT", "Update community");
        communityRepository.save(community);
    }

    public void deleteCommunityByGameId(Long communityId) {
        LogRequest(1L, "DELETE", "Delete community");
        communityRepository.deleteCommunityByGameId(communityId);
    }

    private void LogRequest(Long userId, String action, String description) {
        amqpTemplate.convertAndSend("queue1", "Log from community-service");
        Log log = new Log(userId, "Community-service", action, description);
        HttpEntity<Log> request = new HttpEntity<>(log);
        restTemplate.postForObject("http://logging-service/logs/saveLog", request, Log.class);
        System.out.println(log.toString());
    }

    public void addUserToCommunity(Community community) {
        Game game = restTemplate.getForObject("http://game-service/games/" + community.getGameId(), Game.class);
        if (game != null) {
            User user = restTemplate.getForObject("http://user-service/users/" + community.getUserId(), User.class);
            if (user != null) {
                community.setUserId(user.getUserId());
                communityRepository.save(community);
                LogRequest(1L, "POST", "User added to community");
                System.out.println("User added");
            }
            else {
                LogRequest(1L, "POST", "User didn't add to community");
                System.out.println("Not found user");
            }
        }
        else {
            LogRequest(1L, "POST", "User didn't add to community");
            System.out.println("Not found community");
        }
    }

    @HystrixCommand(
            fallbackMethod = "findCommunityUsersByGameIdFallback",
            threadPoolKey = "findCommunityUsersByGameId"
    )
    public UserCommunity findCommunityUsersByGameId(Long gameId) {
        List<Community> communities = communityRepository.findCommunityByGameId(gameId);
        UserCommunity userCommunity = new UserCommunity(gameId, new ArrayList<>());
        Game game = restTemplate.getForObject("http://game-service/games/" + gameId, Game.class);
        if (game != null) {
            for (Community value : communities) {
                User user = restTemplate.getForObject("http://user-service/users/" + value.getUserId(), User.class);
                if (user != null && value.getGameId().equals(gameId) && user.getUserId().equals(value.getUserId())) {
                    userCommunity.setGameId(gameId);
                    userCommunity.getUsers().add(user);
                }
            }
            if (userCommunity.getUsers() == null) {
                LogRequest(1L, "GET", "Not found any users");
            }
            LogRequest(1L, "GET", "Founded users of this community");
        }
        else {
            LogRequest(1L, "GET", "Not found community");
        }
        return userCommunity;
    }

    public UserCommunity findCommunityUserByGameIdFallback(Long gameId) {
        UserCommunity userLibrary = new UserCommunity(0L, new ArrayList<>());
        User user = new User(0L, "Service not available", "Service not available", "Service not available", 0.0);
        userLibrary.setGameId(0L);
        userLibrary.getUsers().add(user);
        LogRequest(1L, "GET", "Not found library");
        return userLibrary;
    }
}
