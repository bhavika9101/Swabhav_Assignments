package model;
import java.util.*;

public class Grid {
    private final int dimension;
    private final List<Cell> cells = new ArrayList<>();
    private final Map<String, Cell> cellMap = new HashMap<>();
    private final Player playerX;
    private final Player playerO;
    private int filledCellCount;

    public Grid(int dimension, Player playerX, Player playerO){
        this.dimension = dimension;
        this.playerX = playerX;
        this.playerO = playerO;
        this.filledCellCount = 0;
    }

//    test
    public List<Cell> addToCells(Cell cell){
        cells.add(cell);
        return cells;
    }

    public void addToCellMap(String key, Cell value){
        cellMap.put(key, value);
    }

//    test
    public int incrementFilledCellCount() {
        this.filledCellCount++;
        return this.filledCellCount;
    }

    public Cell getCell(int index){
        return cells.get(index);
    }

    public int getFilledCellCount() {
        return filledCellCount;
    }

    public Map<String, Cell> getCellMap() {
        return cellMap;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public int getDimension() {
        return dimension;
    }

}

