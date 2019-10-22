package com.minesweeper.devigetchallenge.dto;

public class RequestGameDto {
    private Integer rows;
    private Integer cols;
    private Integer mines;

    public RequestGameDto() {
    }

    private RequestGameDto(Builder builder) {
        setRows(builder.rows);
        setCols(builder.cols);
        setMines(builder.mines);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(RequestGameDto copy) {
        Builder builder = new Builder();
        builder.rows = copy.getRows();
        builder.cols = copy.getCols();
        builder.mines = copy.getMines();
        return builder;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Integer getMines() {
        return mines;
    }

    public void setMines(Integer mines) {
        this.mines = mines;
    }

    public static final class Builder {
        private Integer rows;
        private Integer cols;
        private Integer mines;

        private Builder() {
        }

        public Builder rows(Integer val) {
            rows = val;
            return this;
        }

        public Builder cols(Integer val) {
            cols = val;
            return this;
        }

        public Builder mines(Integer val) {
            mines = val;
            return this;
        }

        public RequestGameDto build() {
            return new RequestGameDto(this);
        }
    }
}
