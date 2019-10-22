package com.minesweeper.devigetchallenge.service;

import com.minesweeper.devigetchallenge.dto.CellDto;
import com.minesweeper.devigetchallenge.dto.GameDto;
import com.minesweeper.devigetchallenge.dto.RequestGameDto;
import com.minesweeper.devigetchallenge.dto.RevealResult;
import com.minesweeper.devigetchallenge.model.Board;
import com.minesweeper.devigetchallenge.model.Game;
import com.minesweeper.devigetchallenge.model.GameLevel;
import com.minesweeper.devigetchallenge.model.GameStatus;
import com.minesweeper.devigetchallenge.repository.GameRepository;
import com.minesweeper.devigetchallenge.translator.GameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.minesweeper.devigetchallenge.model.GameStatus.INPROGRESS;

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

    public Game createGame(RequestGameDto cell) throws Exception {
        List<Game> oneByStatus = repository.findByStatus(INPROGRESS);
        if(oneByStatus.size() > 0){
            throw new Exception("Game in progress");
        }
        Board board = boardService.createBoard(cell);
        Game game = Game.newBuilder().status(INPROGRESS).level(GameLevel.EASY).board(board).build();
        repository.save(game);
        return game;
    }

    public RevealResult reveal(Long gameId, Integer boardId, CellDto cell) throws Exception {
        List<CellDto> cellDtos = cellService.revealCells(gameId, boardId, cell.getRow(), cell.getCol());
        Integer toWin = 0;
        if(cellDtos.size() != 1 || !cellDtos.get(0).getMine()) {
            toWin = boardService.decrementToWin(cellDtos.size(), boardId);
        }
        return RevealResult.newBuilder().cells(cellDtos).toWin(toWin).build();
    }

    public List<GameDto> getGames() {
        return repository.findAll().stream()
                .map(game -> translator.translate(game))
                .collect(Collectors.toList());
    }

    public void changeStatus(Long id, GameStatus status) throws Exception {
        Game game = repository.findById(id).orElseThrow(() -> new Exception("sd"));
        game.setStatus(status);
        repository.save(game);
    }

    public GameDto loadGame(Long gameId) throws Exception {
        Game game = repository.findById(gameId).orElseThrow(() -> new Exception("sd"));
        return GameDto.newBuilder()
                .id(game.getId())
                .col(game.getBoard().getColSize())
                .row(game.getBoard().getRowSize())
                .cells(cellService.getNotRevealedCells(gameId))
                .boardId(game.getBoard().getId())
                .build();
    }
}
