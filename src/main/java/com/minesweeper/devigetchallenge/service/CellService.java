package com.minesweeper.devigetchallenge.service;

import com.minesweeper.devigetchallenge.dto.CellDto;
import com.minesweeper.devigetchallenge.model.Cell;
import com.minesweeper.devigetchallenge.repository.CellRepository;
import com.minesweeper.devigetchallenge.translator.CellTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CellService {

    private CellRepository cellRepository;
    private CellTranslator translator;

    @Autowired
    public CellService(CellRepository cellRepository, CellTranslator translator) {
        this.cellRepository = cellRepository;
        this.translator = translator;
    }

    public CellDto reveal(Integer boardId, Integer row, Integer col) throws Exception {
        Optional<Cell> revealedCell = cellRepository.findOneByBoardIdAndRowAndCol(boardId, row, col);
        Cell domain = revealedCell.orElseThrow(() -> new Exception("Cell not found"));
        domain.setRevealed(Boolean.TRUE);
        return translator.translate(domain);
    }
}
