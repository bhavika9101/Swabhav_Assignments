package model;

import service.GridService;

public class TicTacToeFacade {
    private final int DIMENSION = 3;
    private GridService gridService;

    public void startGame(){
        Player playerX = new Player('X', false);
        Player playerO = new Player('O', false);

        boolean currentPlayer  = true;

        Grid grid = new Grid(DIMENSION, playerX, playerO);
        gridService = new GridService(grid);

        playerX.setGrid(grid);
        playerO.setGrid(grid);

        gridService.loadGrid();
        gridService.displayGrid();

        while(true){
            if(currentPlayer){
                playerX.setMove();
                if(playerX.makeMove()){
                    break;
                }
                playerX.setMove(); //false
                currentPlayer = false;
                continue;
            }
            playerO.setMove(); //true
            if(playerO.makeMove()){
                break;
            }
            playerO.setMove();
            currentPlayer = true;
        }
        if(playerO.getStatus() == PlayerStatus.WIN){
            return;
        }
        if(playerX.getStatus() == PlayerStatus.WIN){
            return;
        }
        if(playerX.getStatus() == PlayerStatus.TIE){
            System.out.println("GAME DRAW. NO ONE WINS");
        }
    }
}
