package com.transferwise.rockpaperscissors.repository;

import com.transferwise.rockpaperscissors.domain.Game;
import com.transferwise.rockpaperscissors.domain.exception.GameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * InMemoryGameRepository implementation that stores Game information in memory.
 */
@Component
public class InMemoryGameRepository implements GameRepository {

    Map<Long, Game> games = new HashMap<>();

    private static Long nextGameId = 0L;

    @Override
    public Game create(Game game) {
        game.setId(nextGameId());
        game.setScorePlayerOne(0);
        game.setScorePlayerTwo(0);
        games.put(game.getId(), game);
        return game;
    }

    @Override
    public Game find(Long id) {
        Game game = games.get(id);
        if (game == null) {
            throw new GameNotFoundException("The Game Id does not exist in the DB");
        }

        return game;
    }

    @Override
    public void deleteGames() {
        games.clear();
        nextGameId = 0L;
    }

    private static long nextGameId() {
        nextGameId++;
        return nextGameId;
    }
}
