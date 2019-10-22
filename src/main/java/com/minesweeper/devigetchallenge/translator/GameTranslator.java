package com.minesweeper.devigetchallenge.translator;

import com.minesweeper.devigetchallenge.dto.GameDto;
import com.minesweeper.devigetchallenge.dto.GameStatusDto;
import com.minesweeper.devigetchallenge.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameTranslator {

    public GameDto translate(Game domain){
        return GameDto.newBuilder()
                .status(GameStatusDto.valueOf(domain.getStatus().name()))
                .boardId(domain.getBoard().getId()).id(domain.getId()).build();
    }
}
