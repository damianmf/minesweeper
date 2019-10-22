package com.minesweeper.devigetchallenge.controller;

import com.minesweeper.devigetchallenge.dto.CellDto;
import com.minesweeper.devigetchallenge.dto.GameDto;
import com.minesweeper.devigetchallenge.dto.RequestGameDto;
import com.minesweeper.devigetchallenge.dto.RevealResult;
import com.minesweeper.devigetchallenge.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Game controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class GameController {

    private GameService gameService;
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Gets all games.
     *  Retrieve basic configuration of games persisted to show in front list
     * @return the all games
     */
    @GetMapping("/games")
    @ResponseBody
    public List<GameDto> getAllGames() {
        return gameService.getGames();
    }

    /**
     * Gets game by id.
     * Get particular game in order to recreate the last status
     * @param gameId the game id
     * @return the game by id
     * @throws Exception the exception
     */
    @GetMapping("/games/{id}")
    @ResponseBody
    public GameDto getGameById(@PathVariable(value = "id") Long gameId) throws Exception {
        return gameService.loadGame(gameId);
    }

    /**
     * Create game game dto.
     * Create new game requesting rows, columns and mines for the game
     * @param cell the cell
     * @return the game dto
     * @throws Exception the exception
     */
    @PostMapping("/games")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public GameDto createGame(@RequestBody RequestGameDto cell) throws Exception {
        return gameService.createGame(cell);
    }

    /**
     * Reveal reveal result.
     * Reveal particular cell of a game
     * @param id      the id
     * @param boardId the board id
     * @param cell    the cell
     * @return the reveal result
     * @throws Exception the exception
     */
    @PatchMapping("/games/{id}/board/{boardId}/reveal")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public RevealResult reveal(@PathVariable Long id, @PathVariable Integer boardId,
                               @Valid @RequestBody CellDto cell) throws Exception {
        return gameService.reveal(id, boardId, cell);
    }

    /**
     * Status.
     * Change status in order to handle load/pause game
     * @param id   the id
     * @param game the game
     * @throws Exception the exception
     */
    @PatchMapping("/games/{id}/status")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public void status(@PathVariable Long id, @RequestBody GameDto game) throws Exception {
        gameService.changeStatus(id, game.getStatus());
    }

}
