package model;

import service.GridService;
import util.DataValidator;

public class Player {
    private final char symbol;
    private boolean isMove;
    private GridService gridService;
    private PlayerStatus status;

    public Player(char symbol, boolean ifMove){
        this.symbol = symbol;
        this.isMove = ifMove;
        this.status = PlayerStatus.INGAME;
    }

    public void setGrid(Grid grid) {
        this.gridService = new GridService(grid);
    }
//    test
    public boolean makeMove(){
        if(!isMove){
            System.out.println("Not your move.");
            return false;
        }
        status = PlayerStatus.PLAYING;
        System.out.println("Enter cell number (" + symbol +"): ");
        int number = DataValidator.validateInt();
        gridService.fillGrid(number, symbol);
        return gridService.inspect();
    }

    public void setMove(){
        isMove = !isMove;
    }
    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public PlayerStatus getStatus() {
        return status;
    }
}
