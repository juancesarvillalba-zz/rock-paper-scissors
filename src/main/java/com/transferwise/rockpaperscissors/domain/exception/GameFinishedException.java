package com.transferwise.rockpaperscissors.domain.exception;

public class GameFinishedException extends RuntimeException {
    public GameFinishedException(String message) {
        super(message);
    }
}
