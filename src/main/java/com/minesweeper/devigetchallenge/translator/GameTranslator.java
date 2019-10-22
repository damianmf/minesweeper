package com.minesweeper.devigetchallenge.translator;

import com.minesweeper.devigetchallenge.dto.GameDto;
import com.minesweeper.devigetchallenge.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameTranslator {

    public GameDto translate(Game domain){
        return GameDto.newBuilder().status(domain.getStatus()).id(domain.getId()).build();
    }
}
