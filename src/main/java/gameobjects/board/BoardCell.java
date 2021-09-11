package gameobjects.board;

import gameobjects.units.Unit;

public class BoardCell {
    private CellState state;
    private CellColor color;
    private char letterCoordinate;
    private Integer numberCoordinate;
    private Unit unit;

    public BoardCell(char letterCoordinate, Integer numberCoordinate, CellColor color) {
        this.letterCoordinate = letterCoordinate;
        this.numberCoordinate = numberCoordinate;
        state = CellState.FREE;
        this.color = color;
    }

    public BoardCell(int xCoordinate, int yCoordinate, CellColor color) {
        new BoardCell(xCoordinate,yCoordinate, CellState.FREE, color);
    }

    public BoardCell(char letterCoordinate, Integer numberCoordinate, CellState state, CellColor color) {
        this.state = state;
        this.letterCoordinate = letterCoordinate;
        this.numberCoordinate = numberCoordinate;
        this.color = color;
    }

    public BoardCell(int xCoordinate, int yCoordinate, CellState state, CellColor color) {
        this.letterCoordinate = BoardMethods.numberToChar(xCoordinate);
        this.numberCoordinate = yCoordinate + 1;
        this.state = state;
        this.color = color;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public char getLetterCoordinate() {
        return letterCoordinate;
    }

    public void setLetterCoordinate(char letterCoordinate) {
        this.letterCoordinate = letterCoordinate;
    }

    public Integer getNumberCoordinate() {
        return numberCoordinate;
    }

    public void setNumberCoordinate(Integer numberCoordinate) {
        this.numberCoordinate = numberCoordinate;
    }

    public CellColor getColor() {
        return color;
    }

    public void setColor(CellColor color) {
        this.color = color;
    }
}
