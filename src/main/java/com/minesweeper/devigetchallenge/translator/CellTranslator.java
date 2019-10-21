package com.minesweeper.devigetchallenge.translator;

import com.minesweeper.devigetchallenge.dto.CellDto;
import com.minesweeper.devigetchallenge.model.Cell;
import org.springframework.stereotype.Service;

@Service
public class CellTranslator {

    public CellDto translate(Cell domain){
        return CellDto.newBuilder()
                .isMine(domain.getMine())
                .row(domain.getRow())
                .col(domain.getCol())
                .peers(domain.getPeers())
                .build();
    }
}
