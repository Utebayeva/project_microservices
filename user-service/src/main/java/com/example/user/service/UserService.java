package com.example.user.service;

import com.example.user.VO.Community;
import com.example.user.VO.Game;
import com.example.user.VO.ResponseTemplate;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
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
        Game game = restTemplate.getForObject("http://localhost:8082/games/" + user.getGameId(), Game.class);
        rt.setUser(user);
        rt.setGame(game);
        return rt;
    }

    public ResponseTemplate findUserCommunity(Long userId) {
        ResponseTemplate rt = new ResponseTemplate();
        User user = userRepository.findUserByUserId(userId);
        Community community = restTemplate.getForObject("http://localhost:7072/community/" + user.getCommunityId(), Community.class);
        rt.setUser(user);
        rt.setCommunity(community);
        return rt;
    }
}
