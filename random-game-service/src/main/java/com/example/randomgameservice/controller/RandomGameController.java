package com.example.randomgameservice.controller;

import com.example.randomgameservice.service.RandomGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/random-game")
public class RandomGameController {

    @Autowired
    private RandomGameService randomGameService;

    @PostMapping("/getRandomGame/{id}")
    public void getRandomGame(@PathVariable("id") Long userId) {
        randomGameService.getRandomGame(userId);
    }
}
