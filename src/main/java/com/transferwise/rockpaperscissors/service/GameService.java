package com.transferwise.rockpaperscissors.service;

import com.transferwise.rockpaperscissors.domain.Game;
import com.transferwise.rockpaperscissors.domain.GameMove;
import com.transferwise.rockpaperscissors.domain.Move;
import com.transferwise.rockpaperscissors.domain.exception.GameFinishedException;
import com.transferwise.rockpaperscissors.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game create(Game game) {
        return gameRepository.create(game);
    }

    public Game findGame(Long id) {
        return gameRepository.find(id);
    }

    public GameMove play(Long gameId, Move playerOneMove, Move playerTwoMove) {

        Game game = gameRepository.find(gameId);

        if (game.isGameFinished()) {
            throw new GameFinishedException("The Game is already finished");
        }

        GameMove gameMove = new GameMove(playerOneMove, playerTwoMove);

        game.updateScore(gameMove);

        return gameMove;
    }
}
