package com.transferwise.rockpaperscissors.service;

import com.transferwise.rockpaperscissors.ApplicationTests;
import com.transferwise.rockpaperscissors.domain.*;
import com.transferwise.rockpaperscissors.domain.exception.GameFinishedException;
import com.transferwise.rockpaperscissors.domain.exception.GameNotFoundException;
import com.transferwise.rockpaperscissors.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertEquals;

public class GameServiceTest extends ApplicationTests {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;

    @Before
    public void setUp() {
        gameRepository.deleteGames();
    }

    @Test
    public void testShouldCreateAGame() {
        // given
        Game game = new Game();

        //when
        gameService.create(game);

        // then
        assertEquals(Integer.valueOf(0), game.getScorePlayerOne());
        assertEquals(Integer.valueOf(0), game.getScorePlayerTwo());
        assertEquals(GameStatus.CREATED, game.getGameStatus());
    }


    @Test(expected = GameFinishedException.class)
    public void testShouldThrownExceptionWhenGameIsFinished() {
        // given
        Game game = new Game();
        gameService.create(game);
        game.setScorePlayerOne(3);

        // when
        gameService.play(game.getId(), Move.ROCK, Move.SCISSOR);
    }

    @Test
    public void testShouldUpdateScoreAfterPlay() {
        // given
        Game game = new Game();
        gameService.create(game);

        // when
        GameMove gameMove = gameService.play(game.getId(), Move.ROCK, Move.SCISSOR);

        assertEquals(Integer.valueOf(1), game.getScorePlayerOne());
        assertEquals(MoveResult.WIN, gameMove.getMoveResult());
    }

    @Test
    public void testShouldFinishedGameAfterThreeWins() {
        // given
        Game game = new Game();
        gameService.create(game);

        // when
        gameService.play(game.getId(), Move.ROCK, Move.SCISSOR);
        gameService.play(game.getId(), Move.ROCK, Move.SCISSOR);
        gameService.play(game.getId(), Move.ROCK, Move.SCISSOR);

        assertEquals(Integer.valueOf(3), game.getScorePlayerOne());
        assertEquals(GameStatus.FINISHED, game.getGameStatus());
    }

    @Test(expected = GameNotFoundException.class)
    public void testShouldThrownExceptionWhenNotFound() {
        gameService.findGame(555L);
    }
}
