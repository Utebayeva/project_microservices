package com.example.game.service;

import com.example.game.entity.Game;
import com.example.game.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public Game findGameById(Long gameId) {
        return gameRepository.findGameByGameId(gameId);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public Game deleteGame(Long gameId) {
        return gameRepository.deleteGameByGameId(gameId);
    }
}
