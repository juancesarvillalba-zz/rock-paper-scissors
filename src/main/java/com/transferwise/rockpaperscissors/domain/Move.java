package com.transferwise.rockpaperscissors.domain;

public enum Move {

    ROCK {
        @Override
        public boolean winAgainst(Move move) {
            return (SCISSOR == move);
        }
    }, SCISSOR {
        @Override
        public boolean winAgainst(Move move) {
            return (PAPER == move);
        }
    }, PAPER {
        @Override
        public boolean winAgainst(Move move) {
            return (ROCK == move);
        }
    };

    public abstract boolean winAgainst(Move move);
}
