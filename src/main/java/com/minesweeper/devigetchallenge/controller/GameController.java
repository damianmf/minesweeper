package com.minesweeper.devigetchallenge.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GameController {

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
    public String createGame() {
        return null;
    }

    @PatchMapping("/games/{id}/reveal-cell")
    @ResponseBody
    public String reveal() {
        return null;
    }

}
