package com.minesweeper.devigetchallenge.model;

import javax.persistence.*;

@Entity
@Table(name = "cells")
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer col;
    @Column
    private Integer row;
    @Column
    private Boolean isRevealed;
    @Column
    private Boolean isMine;
    @Column
    private Integer peers;
    @Column(name = "board_id")
    private Integer boardId;

    public Cell() {
    }

    private Cell(Builder builder) {
        setId(builder.id);
        setCol(builder.col);
        setRow(builder.row);
        isRevealed = builder.isRevealed;
        isMine = builder.isMine;
        setPeers(builder.peers);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Boolean getRevealed() {
        return isRevealed;
    }

    public void setRevealed(Boolean revealed) {
        isRevealed = revealed;
    }

    public Boolean getMine() {
        return isMine;
    }

    public void setMine(Boolean mine) {
        isMine = mine;
    }

    public Integer getPeers() {
        return peers;
    }

    public void setPeers(Integer peers) {
        this.peers = peers;
    }

    public static final class Builder {
        private Long id;
        private Integer col;
        private Integer row;
        private Boolean isRevealed;
        private Boolean isMine;
        private Integer peers;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder col(Integer val) {
            col = val;
            return this;
        }

        public Builder row(Integer val) {
            row = val;
            return this;
        }

        public Builder isRevealed(Boolean val) {
            isRevealed = val;
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

        public Cell build() {
            return new Cell(this);
        }
    }
}
