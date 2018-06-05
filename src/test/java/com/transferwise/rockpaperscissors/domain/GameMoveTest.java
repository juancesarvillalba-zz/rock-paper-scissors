package com.transferwise.rockpaperscissors.domain;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.transferwise.rockpaperscissors.domain.Move.*;
import static com.transferwise.rockpaperscissors.domain.MoveResult.*;
import static junit.framework.TestCase.assertEquals;

@RunWith(DataProviderRunner.class)
public class GameMoveTest {

    @DataProvider
    public static Object[][] testCreateGameMoveShouldUpdateMoveResult() {
        return new Object[][] {
                {ROCK, SCISSOR, WIN},
                {SCISSOR, PAPER, WIN},
                {PAPER, ROCK, WIN},

                {ROCK, PAPER, LOOSE},
                {SCISSOR, ROCK, LOOSE},
                {PAPER, SCISSOR, LOOSE},

                {ROCK, ROCK, DRAW},
                {SCISSOR, SCISSOR, DRAW},
                {PAPER, PAPER, DRAW},

        };
    }

    @Test
    @UseDataProvider
    public void testCreateGameMoveShouldUpdateMoveResult(Move movePlayerOne, Move movePlayerTwo, MoveResult result) {
        // given
        GameMove gameMove = new GameMove(movePlayerOne, movePlayerTwo);

        //then
        assertEquals(result, gameMove.getMoveResult());
    }
}
