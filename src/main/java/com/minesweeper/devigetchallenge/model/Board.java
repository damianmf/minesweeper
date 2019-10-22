package com.minesweeper.devigetchallenge.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private List<Cell> cells;
    @Column
    private Integer revealToWinCounter;
    @Column
    private Integer colSize;
    @Column
    private Integer rowSize;

    public Board() {
    }

    private Board(Builder builder) {
        setId(builder.id);
        setCells(builder.cells);
        setRevealToWinCounter(builder.revealToWinCounter);
        setColSize(builder.colSize);
        setRowSize(builder.rowSize);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Board copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.cells = copy.getCells();
        builder.revealToWinCounter = copy.getRevealToWinCounter();
        builder.colSize = copy.getColSize();
        builder.rowSize = copy.getRowSize();
        return builder;
    }

    public Integer getColSize() {
        return colSize;
    }

    public void setColSize(Integer colSize) {
        this.colSize = colSize;
    }

    public Integer getRowSize() {
        return rowSize;
    }

    public void setRowSize(Integer rowSize) {
        this.rowSize = rowSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public Integer getRevealToWinCounter() {
        return revealToWinCounter;
    }

    public void setRevealToWinCounter(Integer revealToWinCounter) {
        this.revealToWinCounter = revealToWinCounter;
    }

    public static final class Builder {
        private Integer id;
        private List<Cell> cells = Lists.newArrayList();
        private Integer revealToWinCounter;
        private Integer colSize;
        private Integer rowSize;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder cells(List<Cell> val) {
            cells = val;
            return this;
        }

        public Builder revealToWinCounter(Integer val) {
            revealToWinCounter = val;
            return this;
        }

        public Builder colSize(Integer val) {
            colSize = val;
            return this;
        }

        public Builder rowSize(Integer val) {
            rowSize = val;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
