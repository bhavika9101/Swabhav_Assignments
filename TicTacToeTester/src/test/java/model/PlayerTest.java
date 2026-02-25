package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    @BeforeEach
    void setUp() {
        player = new Player('X', false);
    }

    @Test
    void makeMove() {
        assertFalse(player.makeMove());

//        will fail
//        assertTrue(player.makeMove());
    }
}