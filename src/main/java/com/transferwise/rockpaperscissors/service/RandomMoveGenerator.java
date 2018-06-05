package com.transferwise.rockpaperscissors.service;

import com.transferwise.rockpaperscissors.domain.Move;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomMoveGenerator {

    public Move getRandomMove() {
        Random random = new Random();
        return Move.values()[random.nextInt(Move.values().length)];
    }
}
