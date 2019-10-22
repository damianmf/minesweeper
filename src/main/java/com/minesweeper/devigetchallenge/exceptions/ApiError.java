package com.minesweeper.devigetchallenge.exceptions;

public class ApiError {

    private String error;
    private String message;
    private Integer status;

    public ApiError() {
    }

    public ApiError(String error, String message, Integer status) {
        super();
        this.error = error;
        this.message = message;
        this.status = status;
    }

    private ApiError(Builder builder) {
        setError(builder.error);
        setMessage(builder.message);
        setStatus(builder.status);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ApiError copy) {
        Builder builder = new Builder();
        builder.error = copy.getError();
        builder.message = copy.getMessage();
        builder.status = copy.getStatus();
        return builder;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static final class Builder {
        private String error;
        private String message;
        private Integer status;

        private Builder() {
        }

        public Builder error(String val) {
            error = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder status(Integer val) {
            status = val;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }
}
