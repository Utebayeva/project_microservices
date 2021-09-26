package com.example.game.repository;

import com.example.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGameByGameId(Long gameId);
    Game deleteGameByGameId(Long gameId);
}
