package com.minesweeper.devigetchallenge.dto;

import java.util.List;

public class GameDto {
    private Long id;
    private GameStatusDto status;
    private List<CellDto> cells;
    private Integer row;
    private Integer col;
    private Integer boardId;

    public GameDto() {
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public List<CellDto> getCells() {
        return cells;
    }

    public void setCells(List<CellDto> cells) {
        this.cells = cells;
    }

    public GameStatusDto getStatus() {
        return status;
    }

    public void setStatus(GameStatusDto status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private GameDto(Builder builder) {
        setId(builder.id);
        setStatus(builder.status);
        setCells(builder.cells);
        setRow(builder.row);
        setCol(builder.col);
        setBoardId(builder.boardId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(GameDto copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.status = copy.getStatus();
        builder.cells = copy.getCells();
        builder.row = copy.getRow();
        builder.col = copy.getCol();
        builder.boardId = copy.getBoardId();
        return builder;
    }


    public static final class Builder {
        private Long id;
        private GameStatusDto status;
        private List<CellDto> cells;
        private Integer row;
        private Integer col;
        private Integer boardId;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder status(GameStatusDto val) {
            status = val;
            return this;
        }

        public Builder cells(List<CellDto> val) {
            cells = val;
            return this;
        }

        public Builder row(Integer val) {
            row = val;
            return this;
        }

        public Builder col(Integer val) {
            col = val;
            return this;
        }

        public Builder boardId(Integer val) {
            boardId = val;
            return this;
        }

        public GameDto build() {
            return new GameDto(this);
        }
    }
}
