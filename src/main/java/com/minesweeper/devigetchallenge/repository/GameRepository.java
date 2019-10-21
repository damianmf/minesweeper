package com.minesweeper.devigetchallenge.repository;

import com.minesweeper.devigetchallenge.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
