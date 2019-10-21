package com.minesweeper.devigetchallenge.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private List<Cell> cells;
    @Column
    private Integer revealToWinCounter;

    public Board() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
