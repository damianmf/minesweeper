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
public class BoardService {

    public Board createBoard() {
        List<List<Cell>> preview = generateBoard(10,10);
        MinedBoard minedBoard = mineBoard(preview, 10);
        Board board = numberNeighbors(minedBoard);
        return board;
    }

    private Board numberNeighbors(MinedBoard board) {
        board.getMines().stream().forEach(mine -> setNeighbors(board.getBoard(), mine));
        Board b = Board.newBuilder().build();
        b.getCells().addAll(board.getBoard().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));
        return b;
    }

    private void setNeighbors(List<List<Cell>> board, Cell mine) {
        int rows = board.size();
        int cols = board.get(0).size();
        for(int peerX = -1; peerX<=1; peerX++){
            for(int peerY = -1; peerY<=1; peerY++){
                int x = mine.getRow();
                int y = mine.getCol();
                if(((x + peerX<0) || (x + peerX > (rows - 1))) ||
                        ((y + peerY<0) || (y + peerY > (cols - 1))) ){
                }else{
                    Integer peers = board.get(x + peerX).get(y + peerY).getPeers();
                    board.get(x + peerX).get(y + peerY).setPeers(++peers);
                }

            }
        }
    }

    private MinedBoard mineBoard(List<List<Cell>> board, Integer minesSize) {
        List<Cell> mines = new ArrayList<>();
        int mineCounter = 0;
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        while (mineCounter < minesSize){
            Integer mineRow = randomizer.nextInt(board.size());
            Integer mineCol = randomizer.nextInt(board.get(0).size());
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

    private List<List<Cell>> generateBoard(Integer row, Integer col) {
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
