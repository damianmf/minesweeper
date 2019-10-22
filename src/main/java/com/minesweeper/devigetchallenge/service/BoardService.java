package com.minesweeper.devigetchallenge.service;

import com.minesweeper.devigetchallenge.dto.RequestGameDto;
import com.minesweeper.devigetchallenge.model.Board;
import com.minesweeper.devigetchallenge.model.Cell;
import com.minesweeper.devigetchallenge.model.MinedBoard;
import com.minesweeper.devigetchallenge.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardRepository repository;
    private BoardGameBuilder boardGameBuilder;
    private static Integer DEFAULT_CONFIG = 5;
    @Autowired
    public BoardService(BoardRepository repository, BoardGameBuilder boardGameBuilder) {
        this.repository = repository;
        this.boardGameBuilder=boardGameBuilder;
    }

    public Board createBoard(RequestGameDto cell) throws Exception {
        List<List<Cell>> previewBoard = boardGameBuilder.generateBoard((cell.getRows() == null) ? DEFAULT_CONFIG : cell.getRows(), (cell.getCols() == null) ? DEFAULT_CONFIG : cell.getCols());
        MinedBoard minedBoard = boardGameBuilder.mineBoard(previewBoard, (cell.getMines() == null) ? DEFAULT_CONFIG : cell.getMines());
        Board board = boardGameBuilder.numberNeighbors(minedBoard);
        return board;
    }

    public Integer decrementToWin(int size, Integer boardId) {
        Board board = repository.getOne(boardId);
        board.setRevealToWinCounter(board.getRevealToWinCounter() - size);
        repository.save(board);
        return board.getRevealToWinCounter();
    }
}
