package com.minesweeper.devigetchallenge.repository;

import com.minesweeper.devigetchallenge.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
