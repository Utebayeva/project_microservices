package com.example.game.controller;

import com.example.game.entity.Game;
import com.example.game.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@Slf4j
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("")
    public List<Game> findAllGames() {
        return gameService.findAllGames();
    }

    @GetMapping("/{id}")
    public Game findGameById(@PathVariable("id") Long gameId) {
        return gameService.findGameById(gameId);
    }

    @PostMapping("/saveGame")
    private Game saveGame(@RequestBody Game game) {
        return gameService.saveGame(game);
    }

    @DeleteMapping("/{id}")
    public Game deleteGame(@PathVariable("id") Long gameId) {
        return gameService.deleteGame(gameId);
    }
}
