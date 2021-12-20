package com.example.frontendservice.entity.DTO;

import com.example.frontendservice.entity.Game;
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
