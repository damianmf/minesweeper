package com.minesweeper.devigetchallenge.controller;

import com.minesweeper.devigetchallenge.dto.CellDto;
import com.minesweeper.devigetchallenge.dto.GameDto;
import com.minesweeper.devigetchallenge.dto.RequestGameDto;
import com.minesweeper.devigetchallenge.dto.RevealResult;
import com.minesweeper.devigetchallenge.model.Game;
import com.minesweeper.devigetchallenge.model.GameStatus;
import com.minesweeper.devigetchallenge.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    @ResponseBody
    public List<GameDto> getAllGames() {
        return gameService.getGames();
    }

    @GetMapping("/games/{id}")
    @ResponseBody
    public GameDto getGameById(@PathVariable(value = "id") Long gameId) throws Exception {
        return gameService.loadGame(gameId);
    }

    @PostMapping("/games")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Game createGame(@RequestBody RequestGameDto cell) throws Exception {
        return gameService.createGame(cell);
    }

    @PatchMapping("/games/{id}/board/{boardId}/reveal")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public RevealResult reveal(@PathVariable Long id, @PathVariable Integer boardId,
                               @Valid @RequestBody CellDto cell) throws Exception {
        return gameService.reveal(id, boardId, cell);
    }

    @PatchMapping("/games/{id}/status")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public void status(@PathVariable Long id, @RequestBody GameDto game) throws Exception {
        gameService.changeStatus(id, game.getStatus());
    }

}
