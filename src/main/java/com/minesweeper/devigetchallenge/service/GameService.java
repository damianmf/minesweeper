package com.minesweeper.devigetchallenge.service;

import com.minesweeper.devigetchallenge.dto.CellDto;
import com.minesweeper.devigetchallenge.dto.GameDto;
import com.minesweeper.devigetchallenge.model.Board;
import com.minesweeper.devigetchallenge.model.Cell;
import com.minesweeper.devigetchallenge.model.Game;
import com.minesweeper.devigetchallenge.repository.CellRepository;
import com.minesweeper.devigetchallenge.repository.GameRepository;
import com.minesweeper.devigetchallenge.translator.GameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private GameRepository repository;
    private GameTranslator translator;

    private BoardService boardService;
    private CellService cellService;

    @Autowired
    public GameService(GameRepository repository, GameTranslator translator, BoardService boardService,
                       CellService cellService) {
        this.repository=repository;
        this.translator=translator;
        this.boardService=boardService;
        this.cellService=cellService;
    }

    public Game createGame(){
        Board board = boardService.createBoard();
        Game game = Game.newBuilder().board(board).build();
        repository.save(game);
        return game;
    }

    public CellDto reveal(Long gameId, Integer boardId, CellDto cell) throws Exception {
        return cellService.reveal(boardId, cell.getRow(), cell.getCol());
    }
}
