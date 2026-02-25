package service;

import model.Cell;
import model.Grid;
import model.Player;
import model.PlayerStatus;
import util.ConsoleColor;
import util.DataValidator;

import java.util.Map;

public class GridService {
    private final int DIMENSION;
    private Map<String, Cell> cellMap;

    Grid grid;
    public GridService(Grid grid){
        this.grid = grid;
        this.DIMENSION = grid.getDimension();
        this.cellMap = grid.getCellMap();
    }

    public void loadGrid(){
        int dimension = grid.getDimension();
        char number = '1';
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Cell cell = new Cell(number++);
                grid.addToCells(cell);
                grid.addToCellMap(i+""+j, cell);
            }
        }
    }
    public void displayGrid(){
        int dimension = grid.getDimension();
        int number = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
            for (int j = 0; j < dimension; j++) {
                char symbol = grid.getCell(number++).getSymbol();
                if (symbol == 'X') {
                    System.out.printf("| %s%c%s ", ConsoleColor.RED, symbol, ConsoleColor.RESET);
                } else if (symbol == 'O') {
                    System.out.printf("| %s%c%s ", ConsoleColor.BLUE, symbol, ConsoleColor.RESET);
                } else {
                    System.out.printf("| %c ", symbol);
                }
            }
            System.out.println("|");
        }
        for (int j = 0; j < dimension; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
    public void fillGrid(int number, char symbol){
        int dimension = grid.getDimension();
        number = number-1;
        while (number >= dimension*dimension || grid.getCell(number).ifSymbolSet() || number < 0){
            System.out.println("Cell already occupied or doesn't exist. Choose existing/available one.");
            number = DataValidator.validateInt() -1;
        }
        grid.getCell(number).setSymbol(symbol);
        displayGrid();
    }
    public boolean inspect() {
        grid.incrementFilledCellCount();
        boolean rowWin = inspectRows();
        if(rowWin){
            return true;
        }

        boolean colWin = inspectColumns();
        if(colWin){
            return true;
        }

        boolean leftDiagWin = inspectLeftDiagonal();
        if(leftDiagWin)
            return true;

        boolean rightDiagWin = inspectRightDiagonal();
        if(rightDiagWin)
            return true;

        // tie
        if (grid.getFilledCellCount() == (DIMENSION * DIMENSION)) {
            grid.getPlayerX().setStatus(PlayerStatus.TIE);
            grid.getPlayerO().setStatus(PlayerStatus.TIE);
            return true;
        }

        return false;
    }
//    test
    public boolean inspectRows(){
        for (int i = 0; i < DIMENSION; i++) {
            char first = cellMap.get(i + "" + 0).getSymbol();
            if (first == ' ') continue;

            boolean win = true;
            for (int j = 1; j < DIMENSION; j++) {
                if (cellMap.get(i + "" + j).getSymbol() != first) {
                    win = false;
                    break;
                }
            }
            if(win)
                return declareWinner(first);
        }
        return false;
    }
//    test
    public boolean inspectColumns(){
        for (int j = 0; j < DIMENSION; j++) {
            char first = cellMap.get(0 + "" + j).getSymbol();
            if (first == ' ') continue;

            boolean win = true;
            for (int i = 1; i < DIMENSION; i++) {
                if (cellMap.get(i + "" + j).getSymbol() != first) {
                    win = false;
                    break;
                }
            }
            if(win)
                return declareWinner(first);
        }
        return false;
    }
//    test
    public boolean inspectLeftDiagonal(){
        char leftDiagonal = cellMap.get(0 + "" + 0).getSymbol();
        if (leftDiagonal != ' ') {
            boolean win = true;
            for (int i = 1; i < DIMENSION; i++) {
                if (cellMap.get(i + "" + i).getSymbol() != leftDiagonal) {
                    win = false;
                    break;
                }
            }
            if(win)
                return declareWinner(leftDiagonal);
        }
        return false;
    }
//    test
    public boolean inspectRightDiagonal(){
        char rightDiagonal = cellMap.get(0 + "" + (DIMENSION - 1)).getSymbol();
        if (rightDiagonal != ' ') {
            boolean win = true;
            for (int i = 1; i < DIMENSION; i++) {
                if (cellMap.get(i + "" + (DIMENSION - i - 1)).getSymbol() != rightDiagonal) {
                    win = false;
                    break;
                }
            }
            if (win)
                return declareWinner(rightDiagonal);
        }
        return false;
    }
    private boolean declareWinner(char symbol) {
        Player playerX = grid.getPlayerX();
        Player playerO = grid.getPlayerO();
        if (symbol == 'X') {
            playerX.setStatus(PlayerStatus.WIN);
            playerO.setStatus(PlayerStatus.LOSE);
            System.out.println("PLAYER X WON.");
        } else if (symbol == 'O') {
            playerO.setStatus(PlayerStatus.WIN);
            playerX.setStatus(PlayerStatus.LOSE);
            System.out.println("PLAYER O WON.");
        }
        return true;
    }

    public void setCellMap(Map<String, Cell> cellMap) {
        this.cellMap = cellMap;
    }
}
