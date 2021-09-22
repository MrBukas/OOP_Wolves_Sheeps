package gameobjects.board;

import gameengine.position.Coordinate;
import gameobjects.units.Unit;
import gameobjects.units.players.Sheep;
import gameobjects.units.players.Wolf;

public class BoardCell {
    private CellState state;
    private CellColor color;
    private char letterCoordinate;
    private Integer numberCoordinate;
    private Unit unit = null;

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
        this.letterCoordinate = Coordinate.numberToChar(xCoordinate);
        this.numberCoordinate = yCoordinate + 1;
        this.state = state;
        this.color = color;
        switch (state){
            case WOLF: unit = new Wolf(); break;
            case SHEEP: unit = new Sheep(); break;
        }
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        if (unit instanceof Wolf) this.state = CellState.WOLF;
        if (unit instanceof Sheep) this.state = CellState.SHEEP;
        this.unit = unit;
    }

    public void clearUnit(){
        this.state = CellState.FREE;
        this.unit = null;
    }
}
