package com.transferwise.rockpaperscissors.domain;

import lombok.Getter;

import static com.transferwise.rockpaperscissors.domain.MoveResult.*;

@Getter
/**
 * Class that holds information about Move's for each Player and the result.
 */
public class GameMove {

    private Move playerOneMove;

    private Move playerTwoMove;

    private MoveResult moveResult;

    public GameMove(Move playerOneMove, Move playerTwoMove) {
        this.playerOneMove = playerOneMove;
        this.playerTwoMove = playerTwoMove;
        this.moveResult = evaluateResult();
    }

    private MoveResult evaluateResult() {

        MoveResult moveResult = DRAW;

        if (playerOneMove.winAgainst(playerTwoMove)) {
            moveResult = WIN;
        } else if (playerTwoMove.winAgainst(playerOneMove)) {
            moveResult = LOOSE;
        }

        return moveResult;
    }
}
