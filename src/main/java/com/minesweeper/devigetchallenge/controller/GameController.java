package com.minesweeper.devigetchallenge.controller;

import com.minesweeper.devigetchallenge.dto.CellDto;
import com.minesweeper.devigetchallenge.dto.GameDto;
import com.minesweeper.devigetchallenge.model.Game;
import com.minesweeper.devigetchallenge.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public List<String> getAllGames() {
        return new ArrayList<>();
    }

    @GetMapping("/games/{id}")
    @ResponseBody
    public String getGameById(@PathVariable(value = "id") Long gameId)  {
        return null;
    }

    @PostMapping("/games")
    public Game createGame() {
        return gameService.createGame();
    }

    @PatchMapping("/games/{id}/board/{boardId}/reveal")
    @ResponseBody
    public CellDto reveal(@PathVariable Long id, @PathVariable Integer boardId,
                          @Valid @RequestBody CellDto cell) throws Exception {
        return gameService.reveal(id, boardId, cell);
    }

}
