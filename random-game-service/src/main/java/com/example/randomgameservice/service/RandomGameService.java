package com.example.randomgameservice.service;

import com.example.randomgameservice.entity.DTO.GamesArrayList;
import com.example.randomgameservice.entity.Game;
import com.example.randomgameservice.entity.Library;
import com.example.randomgameservice.entity.Log;
import com.example.randomgameservice.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomGameService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @HystrixCommand(
            fallbackMethod = "getRandomGameFallback",
            threadPoolKey = "getRandomGame"
    )
    public void getRandomGame(Long userId) {
        User user = restTemplate.getForObject("http://user-service/users/" + userId, User.class);
        Game game = restTemplate.getForObject("http://game-service/games/" + userId, Game.class);
        Library library = restTemplate.getForObject("http://library-service/libraries/" + userId, Library.class);
        System.out.println(library);
        System.out.println(user);
        System.out.println(game);
        if (user != null) {
//            Library library = restTemplate.getForObject("http://library-service/libraries/" + userId, Library.class);
            GamesArrayList gamesArrayList = restTemplate.getForObject("http://game-service/games/gamesArrayList", GamesArrayList.class);
            System.out.println(gamesArrayList);
            Game randomGame = gamesArrayList.getGames().get(1);
            System.out.println(randomGame);
            if (library != null && library.getUserId().equals(userId) && !library.getGameId().equals(randomGame.getGameId())) {
                library.setGameId(randomGame.getGameId());
                HttpEntity<Library> request = new HttpEntity<>(library);
                restTemplate.put("http://library-service/libraries/updateLibrary", request, Library.class);
                LogRequest(1L, "POST", "Random game added to user's library");
            }
            else {
                LogRequest(1L, "POST", "User already has this game");
            }
        }
        else {
            LogRequest(1L, "POST", "Not found user");
        }
    }

    public void getRandomGameFallback(Long userId) {
        LogRequest(1L, "POST", "One of services not available");
    }

    private void LogRequest(Long userId, String action, String description) {
        amqpTemplate.convertAndSend("queue1", "Log from random-game-service");
        Log log = new Log(userId, "Random-game-service", action, description);
        HttpEntity<Log> request = new HttpEntity<>(log);
        restTemplate.postForObject("http://logging-service/logs/saveLog", request, Log.class);
        System.out.println(log.toString());
    }
}
