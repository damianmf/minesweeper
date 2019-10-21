package com.minesweeper.devigetchallenge.service;

import com.minesweeper.devigetchallenge.dto.CellDto;
import com.minesweeper.devigetchallenge.dto.GameDto;
import com.minesweeper.devigetchallenge.model.Board;
import com.minesweeper.devigetchallenge.model.Cell;
import com.minesweeper.devigetchallenge.model.Game;
import com.minesweeper.devigetchallenge.repository.GameRepository;
import com.minesweeper.devigetchallenge.translator.GameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private GameRepository repository;
    private GameTranslator translator;

    private BoardService boardService;

    @Autowired
    public GameService(GameRepository repository, GameTranslator translator, BoardService boardService) {
        this.repository=repository;
        this.translator=translator;
        this.boardService=boardService;
    }

    public Game createGame(){
        Board board = boardService.createBoard();
        Game game = Game.newBuilder().board(board).build();
        repository.save(game);
        return game;
    }

    public CellDto reveal(Long gameId, Long boardId, CellDto cell) {
        return null;
    }
}
