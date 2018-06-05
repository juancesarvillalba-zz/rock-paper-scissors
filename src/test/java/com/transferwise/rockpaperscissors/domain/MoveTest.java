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
public class MoveTest {

    @DataProvider
    public static Object[][] testWinAgainst() {
        return new Object[][] {
                {ROCK, SCISSOR, true},
                {SCISSOR, PAPER, true},
                {PAPER, ROCK, true},

                {ROCK, PAPER, false},
                {SCISSOR, ROCK, false},
                {PAPER, SCISSOR, false},

                {ROCK, ROCK, false},
                {SCISSOR, SCISSOR, false},
                {PAPER, PAPER, false},

        };
    }

    @Test
    @UseDataProvider
    public void testWinAgainst(Move movePlayerOne, Move movePlayerTwo, boolean result) {
        // given
        //then
        assertEquals(result, movePlayerOne.winAgainst(movePlayerTwo));
    }
}
