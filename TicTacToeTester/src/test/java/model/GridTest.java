package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    static Grid grid;
    static List<Cell> cells;
    @BeforeAll
    static void setUp() {
        grid = new Grid(3,
                new Player('X', false),
                new Player('O', false));
        cells = new ArrayList<>();
    }

    @Test
    void addToCells() {
        Cell cell = new Cell('X');
        cells.add(cell);
        assertEquals(cells, grid.addToCells(cell));
    }

    @Test
    void incrementFilledCellCount() {
        grid.incrementFilledCellCount();
        assertEquals(1, grid.getFilledCellCount());

//        will fail
        grid.incrementFilledCellCount();
        assertNotEquals(3, grid.getFilledCellCount());

    }
}