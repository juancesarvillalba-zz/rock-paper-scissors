package com.transferwise.rockpaperscissors.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.transferwise.rockpaperscissors.domain.GameStatus.*;
import static com.transferwise.rockpaperscissors.domain.MoveResult.LOOSE;
import static com.transferwise.rockpaperscissors.domain.MoveResult.WIN;

@Data
public class Game {

    private static final int MOVES_TO_WIN = 3;

    private static final String COMPUTER_USER = "Computer";

    private Long id;

    private Integer scorePlayerOne = 0;

    private Integer scorePlayerTwo = 0;

    private String playerOneName;

    private final String playerTwoName = COMPUTER_USER;

    private GameStatus gameStatus = CREATED;

    private List<GameMove> moves = new ArrayList<>();

    @JsonIgnore
    public boolean isGameFinished() {
        return scorePlayerOne == MOVES_TO_WIN || scorePlayerTwo == MOVES_TO_WIN;
    }

    public void updateScore(GameMove gameMove) {

        moves.add(gameMove);

        if (WIN.equals(gameMove.getMoveResult())) {
            scorePlayerOne++;
        }

        if (LOOSE.equals(gameMove.getMoveResult())) {
            scorePlayerTwo++;
        }

        if (isGameFinished()) {
            gameStatus = FINISHED;
        } else {
            gameStatus = PLAYING;
        }
    }
}
