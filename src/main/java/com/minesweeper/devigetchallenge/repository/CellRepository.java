package com.minesweeper.devigetchallenge.repository;

import com.minesweeper.devigetchallenge.model.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {

    Optional<Cell> findOneByBoardIdAndRowAndCol(Integer boardId, Integer row, Integer col);

}
