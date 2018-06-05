package com.transferwise.rockpaperscissors.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transferwise.rockpaperscissors.domain.Game;
import com.transferwise.rockpaperscissors.domain.GameMove;
import com.transferwise.rockpaperscissors.domain.Move;
import com.transferwise.rockpaperscissors.domain.exception.GameFinishedException;
import com.transferwise.rockpaperscissors.domain.exception.GameNotFoundException;
import com.transferwise.rockpaperscissors.service.GameService;
import com.transferwise.rockpaperscissors.service.RandomMoveGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/game")
public class GameController {

    private GameService gameService;

    private RandomMoveGenerator randomMoveGenerator;

    private ObjectMapper objectMapper;

    @Autowired
    public GameController(GameService gameService, RandomMoveGenerator randomMoveGenerator, ObjectMapper objectMapper) {
        this.gameService = gameService;
        this.randomMoveGenerator = randomMoveGenerator;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<>(gameService.create(game), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<Game> findGame(@PathVariable("id") Long id) {
        return new ResponseEntity<>(gameService.findGame(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id:\\d+}/play", method = RequestMethod.PUT)
    public ResponseEntity<GameMove> play(@PathVariable("id") Long id, @RequestBody Move move) {
        return new ResponseEntity<>(gameService.play(id, move, randomMoveGenerator.getRandomMove()), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler( {GameNotFoundException.class})
    public String apiException(GameNotFoundException e) throws Throwable {
        return this.objectMapper.writeValueAsString(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( {GameFinishedException.class})
    public String apiException(GameFinishedException e) throws Throwable {
        return this.objectMapper.writeValueAsString(e.getMessage());
    }
}
