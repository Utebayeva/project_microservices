package com.example.randomgameservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game extends ArrayList<Game> {

    private Long gameId;
    private String title;
    private Double price;
    private String description;
}
