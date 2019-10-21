package com.minesweeper.devigetchallenge.dto;

public class GameDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private GameDto(Builder builder) {
        id = builder.id;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(GameDto copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        return builder;
    }


    public static final class Builder {
        private Long id;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public GameDto build() {
            return new GameDto(this);
        }
    }
}
