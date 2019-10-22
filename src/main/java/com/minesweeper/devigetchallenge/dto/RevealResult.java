package com.minesweeper.devigetchallenge.dto;

import java.util.List;

public class RevealResult {
    private List<CellDto> cells;
    private Integer toWin;

    private RevealResult(Builder builder) {
        setCells(builder.cells);
        setToWin(builder.toWin);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(RevealResult copy) {
        Builder builder = new Builder();
        builder.cells = copy.getCells();
        builder.toWin = copy.getToWin();
        return builder;
    }

    public List<CellDto> getCells() {
        return cells;
    }

    public void setCells(List<CellDto> cells) {
        this.cells = cells;
    }

    public Integer getToWin() {
        return toWin;
    }

    public void setToWin(Integer toWin) {
        this.toWin = toWin;
    }

    public static final class Builder {
        private List<CellDto> cells;
        private Integer toWin;

        private Builder() {
        }

        public Builder cells(List<CellDto> val) {
            cells = val;
            return this;
        }

        public Builder toWin(Integer val) {
            toWin = val;
            return this;
        }

        public RevealResult build() {
            return new RevealResult(this);
        }
    }
}
