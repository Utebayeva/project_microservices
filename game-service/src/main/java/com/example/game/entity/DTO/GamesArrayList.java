package com.example.game.entity.DTO;

import com.example.game.entity.Game;
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
