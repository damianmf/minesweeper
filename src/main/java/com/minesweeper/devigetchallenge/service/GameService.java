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
    private CellRepository cellRepository;

    @Autowired
    public GameService(GameRepository repository, GameTranslator translator, BoardService boardService, CellRepository cellRepository) {
        this.repository=repository;
        this.translator=translator;
        this.boardService=boardService;
        this.cellRepository = cellRepository;
    }

    public Game createGame(){
        Board board = boardService.createBoard();
        Game game = Game.newBuilder().board(board).build();
        repository.save(game);
        return game;
    }

    public CellDto reveal(Long gameId, Integer boardId, CellDto cell) throws Exception {
        Optional<Cell> revealedCell = cellRepository.findOneByBoardIdAndRowAndCol(boardId, cell.getRow(), cell.getCol());
        Cell domain = revealedCell.orElseThrow(() -> new Exception("Cell not found"));
        domain.setRevealed(Boolean.TRUE);
        CellDto dto = CellDto.newBuilder()
                .col(domain.getCol())
                .row(domain.getRow())
                .isMine(domain.getMine())
                .build();
        return dto;
    }
}
