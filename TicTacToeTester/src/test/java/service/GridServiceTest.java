package service;

import model.Cell;
import model.Grid;
import model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GridServiceTest {
    static GridService gridService;
    static Map<String, Cell> cellMap;
    static Player playerX;
    static Player playerO;
    @BeforeAll
    static void setUp() {
        cellMap = new HashMap<>();
        playerX = new Player('X',false);
        playerO = new Player('O',false);
        gridService = new GridService(
                new Grid(3, playerX, playerO));
        gridService.loadGrid();
        gridService.setCellMap(cellMap);
    }
    @AfterEach
    void cleanUp(){
        cellMap.clear();
    }

    @Test
    void inspectRowsForX() {
        Cell cell = new Cell('X');
        cellMap.put("00", cell);
        cellMap.put("01", cell);
        cellMap.put("02", cell);
        assertTrue(gridService.inspectRows());


        cellMap.put("10", cell);
        cellMap.put("11", cell);
        cellMap.put("12", cell);
        assertTrue(gridService.inspectRows());

        cellMap.put("20", cell);
        cellMap.put("21", cell);
        cellMap.put("22", cell);
        assertTrue(gridService.inspectRows());
    }

    @Test
    void inspectRowsForO() {
        Cell cell = new Cell('O');
        cellMap.put("00", cell);
        cellMap.put("01", cell);
        cellMap.put("02", cell);
        assertTrue(gridService.inspectRows());

        cellMap.put("10", cell);
        cellMap.put("11", cell);
        cellMap.put("12", cell);
        assertTrue(gridService.inspectRows());

        cellMap.put("20", cell);
        cellMap.put("21", cell);
        cellMap.put("22", cell);
        assertTrue(gridService.inspectRows());
    }

    @Test
    void inspectColumnsForX() {
        Cell cell = new Cell('O');
        cellMap.put("00", cell);
        cellMap.put("10", cell);
        cellMap.put("20", cell);
        assertTrue(gridService.inspectColumns());


        cellMap.put("01", cell);
        cellMap.put("11", cell);
        cellMap.put("21", cell);
        assertTrue(gridService.inspectColumns());

        cellMap.put("02", cell);
        cellMap.put("12", cell);
        cellMap.put("22", cell);
        assertTrue(gridService.inspectColumns());
    }

    @Test
    void inspectColumnsForO() {
        Cell cell = new Cell('X');
        cellMap.put("00", cell);
        cellMap.put("10", cell);
        cellMap.put("20", cell);
        assertTrue(gridService.inspectColumns());

        cellMap.put("01", cell);
        cellMap.put("11", cell);
        cellMap.put("21", cell);
        assertTrue(gridService.inspectColumns());

        cellMap.put("02", cell);
        cellMap.put("12", cell);
        cellMap.put("22", cell);
        assertTrue(gridService.inspectColumns());
    }

    @Test
    void inspectLeftDiagonalForO() {
        Cell cell = new Cell('O');
        cellMap.put("00", cell);
        cellMap.put("11", cell);
        cellMap.put("22", cell);
        assertTrue(gridService.inspectLeftDiagonal());
    }
    @Test
    void inspectLeftDiagonalForX() {
        Cell cell = new Cell('X');
        cellMap.put("00", cell);
        cellMap.put("11", cell);
        cellMap.put("22", cell);
        assertTrue(gridService.inspectLeftDiagonal());
    }

    @Test
    void inspectRightDiagonalForO() {
        Cell cell = new Cell('O');
        cellMap.put("02", cell);
        cellMap.put("11", cell);
        cellMap.put("20", cell);
        assertTrue(gridService.inspectRightDiagonal());
    }
    @Test
    void inspectRightDiagonalForX() {
        Cell cell = new Cell('X');
        cellMap.put("02", cell);
        cellMap.put("11", cell);
        cellMap.put("20", cell);
        assertTrue(gridService.inspectRightDiagonal());
    }
}