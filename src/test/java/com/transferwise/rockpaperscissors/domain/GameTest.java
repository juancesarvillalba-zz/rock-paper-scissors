package com.transferwise.rockpaperscissors.domain;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

@RunWith(DataProviderRunner.class)
public class GameTest {

    @DataProvider
    public static Object[][] testUpdateScoreShouldIncreaseScorePlayerOne() {
        return new Object[][] {
                {Move.ROCK, Move.SCISSOR},
                {Move.SCISSOR, Move.PAPER},
                {Move.PAPER, Move.ROCK}
        };
    }

    @Test
    @UseDataProvider
    public void testUpdateScoreShouldIncreaseScorePlayerOne(Move movePlayerOne, Move movePlayerTwo) {
        // given
        Game game = new Game();
        GameMove gameMove = new GameMove(movePlayerOne, movePlayerTwo);

        assertEquals(Integer.valueOf(0), game.getScorePlayerOne());
        assertEquals(Integer.valueOf(0), game.getScorePlayerTwo());

        //when
        game.updateScore(gameMove);

        //then
        assertEquals(Integer.valueOf(1), game.getScorePlayerOne());
        assertEquals(Integer.valueOf(0), game.getScorePlayerTwo());
    }

    @DataProvider
    public static Object[][] testUpdateScoreShouldIncreaseScorePlayerTwo() {
        return new Object[][] {
                {Move.ROCK, Move.SCISSOR},
                {Move.SCISSOR, Move.PAPER},
                {Move.PAPER, Move.ROCK}
        };
    }

    @Test
    @UseDataProvider
    public void testUpdateScoreShouldIncreaseScorePlayerTwo(Move movePlayerOne, Move movePlayerTwo) {
        // given
        Game game = new Game();
        GameMove gameMove = new GameMove(movePlayerTwo, movePlayerOne);

        assertEquals(Integer.valueOf(0), game.getScorePlayerOne());
        assertEquals(Integer.valueOf(0), game.getScorePlayerTwo());

        //when
        game.updateScore(gameMove);

        //then
        assertEquals(Integer.valueOf(0), game.getScorePlayerOne());
        assertEquals(Integer.valueOf(1), game.getScorePlayerTwo());
    }

    @DataProvider
    public static Object[][] testUpdateScoreShouldNotChangeWhenSameMove() {
        return new Object[][] {
                {Move.ROCK},
                {Move.SCISSOR},
                {Move.PAPER}
        };
    }

    @Test
    @UseDataProvider
    public void testUpdateScoreShouldNotChangeWhenSameMove(Move move) {
        // given
        Game game = new Game();
        GameMove gameMove = new GameMove(move, move);

        assertEquals(Integer.valueOf(0), game.getScorePlayerOne());
        assertEquals(Integer.valueOf(0), game.getScorePlayerTwo());

        //when
        game.updateScore(gameMove);

        //then
        assertEquals(Integer.valueOf(0), game.getScorePlayerOne());
        assertEquals(Integer.valueOf(0), game.getScorePlayerTwo());
    }

    @Test
    public void testUpdateScoreShouldChangeGameStatusToFinished() {
        // given
        Game game = new Game();

        assertEquals(GameStatus.CREATED, game.getGameStatus());

        //when
        game.updateScore(new GameMove(Move.ROCK, Move.SCISSOR));
        game.updateScore(new GameMove(Move.ROCK, Move.SCISSOR));
        game.updateScore(new GameMove(Move.ROCK, Move.SCISSOR));

        //then
        assertEquals(GameStatus.FINISHED, game.getGameStatus());
        assertTrue(game.isGameFinished());
    }

    @Test
    public void testUpdateScoreShouldChangeGameStatusToPlaying() {
        // given
        Game game = new Game();

        assertEquals(GameStatus.CREATED, game.getGameStatus());

        //when
        game.updateScore(new GameMove(Move.ROCK, Move.SCISSOR));
        game.updateScore(new GameMove(Move.ROCK, Move.SCISSOR));

        //then
        assertEquals(GameStatus.PLAYING, game.getGameStatus());
        assertFalse(game.isGameFinished());
    }
}
