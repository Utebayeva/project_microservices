package com.example.game.controller;

import com.example.game.entity.DTO.GamesArrayList;
import com.example.game.entity.Game;
import com.example.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("")
    public List<Game> findAllGames() {
        return gameService.findAllGames();
    }

    @GetMapping("/{id}")
    public Optional<Game> findGameById(@PathVariable("id") Long gameId) {
        return gameService.findGameById(gameId);
    }

    @PostMapping("/saveGame")
    private void saveGame(@RequestBody Game game) {
        gameService.saveGame(game);
    }

    @PutMapping("/updateGame")
    private void updateGame(@RequestBody Game game) {
        gameService.updateGame(game);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable("id") Long gameId) {
        gameService.deleteGame(gameId);
    }

    @GetMapping("/gamesArrayList")
    public GamesArrayList GetGamesDto() {
        ArrayList<Game> games = (ArrayList<Game>) gameService.findAllGames();
        GamesArrayList gamesArrayList = new GamesArrayList(games);
        return gamesArrayList;
    }
}
