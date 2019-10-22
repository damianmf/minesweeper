package com.minesweeper.devigetchallenge.model;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Board board;
    @Column
    @Enumerated(EnumType.STRING)
    private GameStatus status;
    @Column
    @Enumerated(EnumType.STRING)
    private GameLevel level;

    public Game() {
    }

    private Game(Builder builder) {
        setId(builder.id);
        setBoard(builder.board);
        setStatus(builder.status);
        setLevel(builder.level);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Game copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.board = copy.getBoard();
        builder.status = copy.getStatus();
        builder.level = copy.getLevel();
        return builder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameLevel getLevel() {
        return level;
    }

    public void setLevel(GameLevel level) {
        this.level = level;
    }

    public static final class Builder {
        private Long id;
        private Board board;
        private GameStatus status;
        private GameLevel level;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder board(Board val) {
            board = val;
            return this;
        }

        public Builder status(GameStatus val) {
            status = val;
            return this;
        }

        public Builder level(GameLevel val) {
            level = val;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}
