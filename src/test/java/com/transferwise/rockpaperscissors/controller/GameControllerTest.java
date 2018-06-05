package com.transferwise.rockpaperscissors.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transferwise.rockpaperscissors.domain.Game;
import com.transferwise.rockpaperscissors.domain.GameStatus;
import com.transferwise.rockpaperscissors.domain.Move;
import com.transferwise.rockpaperscissors.domain.exception.GameNotFoundException;
import com.transferwise.rockpaperscissors.repository.GameRepository;
import com.transferwise.rockpaperscissors.service.RandomMoveGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private RandomMoveGenerator randomMoveGenerator;

    private JacksonTester<Object> jacksonTester;

    @Before
    public void setUp() {

        JacksonTester.initFields(this, new ObjectMapper());

        Game game = new Game();
        game.setId(1L);
        game.setPlayerOneName("Juan");
        game.setScorePlayerOne(1);
        game.setScorePlayerTwo(2);
        game.setGameStatus(GameStatus.PLAYING);

        Mockito.when(gameRepository.find(1L)).thenReturn(game);
        Mockito.when(gameRepository.find(5L)).thenThrow(GameNotFoundException.class);

    }

    @Test
    public void testGetGame() throws Exception {
        mvc.perform(get("/api/game/1")
                .contentType(APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("playerOneName", is("Juan")))
                .andExpect(jsonPath("playerTwoName", is("Computer")))
                .andExpect(jsonPath("scorePlayerOne", is(1)))
                .andExpect(jsonPath("scorePlayerTwo", is(2)))
                .andExpect(jsonPath("gameStatus", is("PLAYING")));
    }

    @Test
    public void testGetGameShouldReturnNotFound() throws Exception {
        mvc.perform(get("/api/game/5")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPlayShouldWin() throws Exception {

        Mockito.when(randomMoveGenerator.getRandomMove()).thenReturn(Move.ROCK);

        mvc.perform(put("/api/game/1/play")
                .contentType(APPLICATION_JSON)
                .content(jacksonTester.write(Move.PAPER).getJson()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("moveResult", is("WIN")))
                .andExpect(jsonPath("playerOneMove", is("PAPER")))
                .andExpect(jsonPath("playerTwoMove", is("ROCK")));
    }

    @Test
    public void testPlayShouldLoose() throws Exception {

        Mockito.when(randomMoveGenerator.getRandomMove()).thenReturn(Move.SCISSOR);

        mvc.perform(put("/api/game/1/play")
                .contentType(APPLICATION_JSON)
                .content(jacksonTester.write(Move.PAPER).getJson()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("moveResult", is("LOOSE")))
                .andExpect(jsonPath("playerOneMove", is("PAPER")))
                .andExpect(jsonPath("playerTwoMove", is("SCISSOR")));
    }

    @Test
    public void testCreateGame() throws Exception {
        Game game = new Game();
        game.setId(2L);
        game.setPlayerOneName("Test");

        Mockito.when(gameRepository.create(Mockito.any(Game.class))).thenReturn(game);

        mvc.perform(post("/api/game")
                .contentType(APPLICATION_JSON)
                .content(jacksonTester.write(game).getJson()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id", is(2)))
                .andExpect(jsonPath("playerOneName", is("Test")))
                .andExpect(jsonPath("playerTwoName", is("Computer")))
                .andExpect(jsonPath("scorePlayerOne", is(0)))
                .andExpect(jsonPath("scorePlayerTwo", is(0)))
                .andExpect(jsonPath("gameStatus", is("CREATED")));
    }
}
