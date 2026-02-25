package model;

public class Cell {
    private char symbol;
    private boolean symbolSet = false;

    public Cell(char symbol) {
        this.symbol = symbol;
    }

//    test
    public char setSymbol(char symbol){
        symbolSet = true;
        this.symbol = symbol;
        return this.symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean ifSymbolSet(){
        return symbolSet;
    }
}
