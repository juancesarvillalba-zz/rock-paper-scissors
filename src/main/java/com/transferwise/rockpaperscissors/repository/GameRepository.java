package com.transferwise.rockpaperscissors.repository;

import com.transferwise.rockpaperscissors.domain.Game;

public interface GameRepository {

    Game create(Game game);

    Game find(Long id);

    void deleteGames();

}
