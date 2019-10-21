package com.minesweeper.devigetchallenge.service;

import com.google.common.collect.Lists;
import com.minesweeper.devigetchallenge.dto.GameDto;
import com.minesweeper.devigetchallenge.model.Board;
import com.minesweeper.devigetchallenge.model.Cell;
import com.minesweeper.devigetchallenge.model.Game;
import com.minesweeper.devigetchallenge.repository.GameRepository;
import com.minesweeper.devigetchallenge.translator.GameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameRepository repository;
    private GameTranslator translator;

    @Autowired
    public GameService(GameRepository repository, GameTranslator translator) {
        this.repository=repository;
        this.translator=translator;
    }

    public GameDto createGame(){
        return translator.translate(repository.save(Game.newBuilder()
                .board(Board.newBuilder()
                        .cells(Lists.newArrayList(Cell.newBuilder().build()))
                        .build())
                .build()));
    }
}
