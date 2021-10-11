package com.example.user.service;

import com.example.user.VO.Community;
import com.example.user.VO.Game;
import com.example.user.VO.Log;
import com.example.user.VO.ResponseTemplate;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        return userRepository.findUserByUserId(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUser(Long userId) {
        return userRepository.deleteByUserId(userId);
    }


    public ResponseTemplate findUserGames(Long userId) {
        ResponseTemplate rt = new ResponseTemplate();
        User user = userRepository.findUserByUserId(userId);
        Game game = restTemplate.getForObject("http://game-service/games/" + user.getGameId(), Game.class);
        rt.setUser(user);
        rt.setGame(game);
        return rt;
    }

    public ResponseTemplate findUserCommunity(Long userId) {
        ResponseTemplate rt = new ResponseTemplate();
        User user = userRepository.findUserByUserId(userId);
        Community community = restTemplate.getForObject("http://community-service/communities/" + user.getCommunityId(), Community.class);
        rt.setUser(user);
        rt.setCommunity(community);
        return rt;
    }

    public ResponseTemplate findLog(Long userId) {
        ResponseTemplate rt = new ResponseTemplate();
        User user = userRepository.findUserByUserId(userId);
        Log log = restTemplate.getForObject("http://logging-service/logs/" + user.getLogId(), Log.class);
        rt.setUser(user);
        rt.setLog(log);
        return rt;
    }
}
