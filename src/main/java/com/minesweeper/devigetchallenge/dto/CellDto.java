package com.minesweeper.devigetchallenge.dto;

public class CellDto {

    private Integer row;
    private Integer col;
    private Boolean isMine;
    private Integer peers;


    public CellDto() {
    }

    private CellDto(Builder builder) {
        setRow(builder.row);
        setCol(builder.col);
        isMine = builder.isMine;
        setPeers(builder.peers);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(CellDto copy) {
        Builder builder = new Builder();
        builder.row = copy.getRow();
        builder.col = copy.getCol();
        builder.isMine = copy.getMine();
        builder.peers = copy.getPeers();
        return builder;
    }

    public Integer getPeers() {
        return peers;
    }

    public void setPeers(Integer peers) {
        this.peers = peers;
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

    public Boolean getMine() {
        return isMine;
    }

    public void setMine(Boolean mine) {
        isMine = mine;
    }

    public static final class Builder {
        private Integer row;
        private Integer col;
        private Boolean isMine;
        private Integer peers;

        private Builder() {
        }

        public Builder row(Integer val) {
            row = val;
            return this;
        }

        public Builder col(Integer val) {
            col = val;
            return this;
        }

        public Builder isMine(Boolean val) {
            isMine = val;
            return this;
        }

        public Builder peers(Integer val) {
            peers = val;
            return this;
        }

        public CellDto build() {
            return new CellDto(this);
        }
    }
}
