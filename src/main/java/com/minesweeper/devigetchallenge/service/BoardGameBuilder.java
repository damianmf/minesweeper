package com.minesweeper.devigetchallenge.service;

import com.minesweeper.devigetchallenge.model.Board;
import com.minesweeper.devigetchallenge.model.Cell;
import com.minesweeper.devigetchallenge.model.MinedBoard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class BoardGameBuilder {

    public Board numberNeighbors(MinedBoard mined) throws Exception {
        int rows = mined.getBoard().size();
        int cols = mined.getBoard().stream().findFirst().map(row -> row.size()).orElseThrow(()-> new Exception("Not row found"));
        for(int z = 0; z<mined.getMines().size();z++){
            for(int peerX = -1; peerX<=1; peerX++){
                for(int peerY = -1; peerY<=1; peerY++){
                    int y = mined.getMines().get(z).getRow();
                    int x = mined.getMines().get(z).getCol();
                    if (((x + peerX >= 0) && (x + peerX <= (rows - 1))) &&
                            ((y + peerY >= 0) && (y + peerY <= (cols - 1)))) {
                                Integer peers = mined.getBoard().get(x + peerX).get(y + peerY).getPeers();
                                peers = peers + 1;
                                mined.getBoard().get(x + peerX).get(y + peerY).setPeers(peers);
                            }
                }
            }

        }
        Board board = Board.newBuilder().colSize(cols).rowSize(rows).revealToWinCounter((rows*cols) - mined.getMines().size()).build();
        board.getCells().addAll(mined.getBoard().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));
        return board;
    }

    public MinedBoard mineBoard(List<List<Cell>> board, Integer minesSize) {
        List<Cell> mines = new ArrayList<>();
        int mineCounter = 0;
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        while (mineCounter < minesSize){
            Integer mineRow = randomizer.nextInt(board.size()-1);
            Integer mineCol = randomizer.nextInt(board.get(0).size()-1);
            if(putMines(board, mineRow, mineCol)){
                mines.add(board.get(mineRow).get(mineCol));
                mineCounter++;
            }
        }
        return MinedBoard.newBuilder().board(board).mines(mines).build();
    }

    private boolean putMines(List<List<Cell>> board, Integer mineRow, Integer minesSize) {
        Cell cell = board.get(mineRow).get(minesSize);
        if(cell.getMine())
            return false;
        cell.setMine(Boolean.TRUE);
        return true;
    }

    public List<List<Cell>> generateBoard(Integer row, Integer col) {
        List board = new ArrayList<List<Cell>>();
        for (int i = 0; i < row; i++) {
            board.add(new ArrayList<Cell>());
            for (int y = 0; y < col; y++) {
                List<Cell> r = (List<Cell>) board.get(i);
                r.add(Cell.newBuilder()
                        .col(i)
                        .row(y)
                        .isMine(Boolean.FALSE)
                        .isRevealed(Boolean.FALSE)
                        .peers(0)
                        .build());
            }
        }
        return board;
    }
}
