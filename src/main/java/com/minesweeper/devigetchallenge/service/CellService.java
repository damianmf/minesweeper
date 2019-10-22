package com.minesweeper.devigetchallenge.service;

import com.google.common.collect.Lists;
import com.minesweeper.devigetchallenge.dto.CellDto;
import com.minesweeper.devigetchallenge.exceptions.ResourceNotFoundException;
import com.minesweeper.devigetchallenge.model.Cell;
import com.minesweeper.devigetchallenge.model.Game;
import com.minesweeper.devigetchallenge.model.GameStatus;
import com.minesweeper.devigetchallenge.repository.BoardRepository;
import com.minesweeper.devigetchallenge.repository.CellRepository;
import com.minesweeper.devigetchallenge.repository.GameRepository;
import com.minesweeper.devigetchallenge.translator.CellTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CellService {
    private CellRepository cellRepository;
    private CellTranslator translator;
    private GameRepository gameRepository;

    @Autowired
    public CellService(CellRepository cellRepository, CellTranslator translator, GameRepository gameRepository) {
        this.cellRepository = cellRepository;
        this.translator = translator;
        this.gameRepository = gameRepository;
    }

    public Cell reveal(Integer boardId, Integer row, Integer col) throws Exception {
        Optional<Cell> revealedCell = cellRepository.findOneByBoardIdAndRowAndCol(boardId, row, col);
        Cell domain = revealedCell.orElseThrow(() -> new Exception("Cell not found"));
        return domain;
    }

    /**
     * Handle recursively adjacent reveal cell in case it applies
     * */
    private List<Cell> revealPeears(Cell domain, Integer row, Integer col) throws Exception {
        List<Cell> rpears = new ArrayList<>();
        for(int peerX = -1; peerX<=1; peerX++){
            for(int peerY = -1; peerY<=1; peerY++){
                int x = domain.getCol();
                int y = domain.getRow();
                if (((x + peerX >= 0) && (x + peerX <= (row - 1))) &&
                        ((y + peerY >= 0) && (y + peerY <= (col - 1)))) {
                            Optional<Cell> revealedCell = cellRepository.findOneByBoardIdAndRowAndCol(domain.getBoardId(), y + peerY, x + peerX);
                            Cell adjacent = revealedCell.orElseThrow(() -> new Exception("Cell not found"));
                            if(!adjacent.getRevealed() && !adjacent.getMine()) {
                                adjacent.setRevealed(Boolean.TRUE);
                                rpears.add(adjacent);
                                if(adjacent.getPeers() == 0l){
                                    rpears.addAll(revealPeears(adjacent, row, col));
                                }
                            }
                        }
            }
        }
        return rpears;
    }

    /**
     * Reveal main cell, change status if a mine is found, start adjacent flow in case the cell has no peers
     * */
    public List<CellDto> revealCells(Long gameId, Integer boardId, Integer row, Integer col) throws Exception {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new Exception("Cell not found"));;
        Cell reveal = this.reveal(boardId, row, col);
        reveal.setRevealed(Boolean.TRUE);
        ArrayList<Cell> revealedCells = Lists.newArrayList();
        revealedCells.add(reveal);
        if(reveal.getMine()){
            game.setStatus(GameStatus.FINISH);
            gameRepository.save(game);
        }else if(reveal.getPeers() == 0l){
            revealedCells.addAll(revealPeears(reveal, game.getBoard().getRowSize(), game.getBoard().getColSize()));
        }
        return revealedCells.stream().map(c -> translator.translate(c)).collect(Collectors.toList());
    }

    public List<CellDto> getNotRevealedCells(Long gameId) throws Exception {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new ResourceNotFoundException(String.format("Game %d not found", gameId )));
        return game.getBoard().getCells().stream()
                .filter(cell -> cell.getRevealed())
                .map(cell -> translator.translate(cell))
                .collect(Collectors.toList());
    }
}
