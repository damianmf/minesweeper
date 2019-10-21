package com.minesweeper.devigetchallenge.model;

import java.util.List;

public class MinedBoard {
    private List<List<Cell>> board;
    private List<Cell> mines;

    private MinedBoard(Builder builder) {
        setBoard(builder.board);
        setMines(builder.mines);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(MinedBoard copy) {
        Builder builder = new Builder();
        builder.board = copy.getBoard();
        builder.mines = copy.getMines();
        return builder;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public List<Cell> getMines() {
        return mines;
    }

    public void setMines(List<Cell> mines) {
        this.mines = mines;
    }


    public static final class Builder {
        private List<List<Cell>> board;
        private List<Cell> mines;

        private Builder() {
        }

        public Builder board(List<List<Cell>> val) {
            board = val;
            return this;
        }

        public Builder mines(List<Cell> val) {
            mines = val;
            return this;
        }

        public MinedBoard build() {
            return new MinedBoard(this);
        }
    }
}
