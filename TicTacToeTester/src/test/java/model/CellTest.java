package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell cell;
    @BeforeEach
    void setUp() {
        cell = new Cell('X');
    }

    @Test
    void setSymbol() {
        assertEquals('X', cell.setSymbol('X'));
        assertEquals('O', cell.setSymbol('O'));

//        will fail
        assertNotEquals('O', cell.setSymbol('X'));
        assertNotEquals('X', cell.setSymbol('O'));

    }
}