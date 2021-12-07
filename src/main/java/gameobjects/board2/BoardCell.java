package gameobjects.board2;

import gameengine.position.BoardConverter;
import gameobjects.board.CellColor;
import gameobjects.board.CellState;
import gameobjects.units.Unit;

import java.util.LinkedList;
import java.util.List;

public class BoardCell {
    private int id;
    private CellState state;
    private CellColor color;
//    Если соседней ячейки нет, то её индекс -1
    private int[] adjacentCells = new int[8];


    public BoardCell(CellState state, CellColor color,int id) {
        this.state = state;
        this.color = color;
        this.id = id;
    }



    public int[] getAdjacentCells() {
        return adjacentCells;
    }

    public List<Integer> getAvailableCells(){
        List<Integer> list = new LinkedList<>();
        int[] cellsToGo = new int[state.getWalkDirection().getSteps().length];
        for (int i = 0; i < cellsToGo.length; i++) {
            int index = state.getWalkDirection().getSteps()[i];
            if(adjacentCells[index] != -1){
                list.add(adjacentCells[index]);
            }
        }
        return list;
    }

    public void setAdjacentCells(int[] adjacentCells) {
        this.adjacentCells = adjacentCells;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void clearUnit(){
        state = CellState.FREE;
    }

    public CellColor getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    private char widthToBoardLetter(int width){
        return (char) ((char) width + 65);
    }
    @Override
    public String toString(){
        return String.valueOf(widthToBoardLetter(id % 8)) + (8 - (id/8));
    }
}
