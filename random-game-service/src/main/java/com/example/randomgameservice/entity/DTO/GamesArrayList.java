package com.example.randomgameservice.entity.DTO;

import com.example.randomgameservice.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamesArrayList {
    private ArrayList<Game> games;
}
