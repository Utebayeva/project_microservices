package com.example.game.service;

import com.example.game.entity.Game;
import com.example.game.entity.Log;
import com.example.game.repository.GameRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public List<Game> findAllGames() {
        LogRequest(1L, "GET", "Find all games");
        return gameRepository.findAll();
    }

    public Optional<Game> findGameById(Long gameId) {
        LogRequest(1L, "GET", "Find game");
        return gameRepository.findById(gameId);
    }

    public void saveGame(Game game) {
        LogRequest(1L, "POST", "Save game");
        gameRepository.save(game);
    }

    public void updateGame(Game game) {
        LogRequest(1L, "PUT", "Update game");
        gameRepository.save(game);
    }

    public void deleteGame(Long gameId) {
        LogRequest(1L, "DELETE", "Delete game");
        gameRepository.deleteById(gameId);
    }

    private void LogRequest(Long userId, String action, String description) {
        amqpTemplate.convertAndSend("queue1", "Log from game-service");
        Log log = new Log(userId, "Game-service", action, description);
        HttpEntity<Log> request = new HttpEntity<>(log);
        restTemplate.postForObject("http://logging-service/logs/saveLog", request, Log.class);
        System.out.println(log.toString());
    }
}
