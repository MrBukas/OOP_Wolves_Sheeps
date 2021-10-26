package gameobjects.board2;

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


    public BoardCell(CellState state, CellColor color) {
        this.state = state;
        this.color = color;
    }

    public int[] getAdjacentCells() {
        return adjacentCells;
    }

    public List<Integer> getAvailableCells(){
        List<Integer> list = new LinkedList<>();
        int[] cellsToGo = new int[state.getWalkDirection().getSteps().length];
        for (int i = 0; i < cellsToGo.length; i++) {
            if(state.getWalkDirection().getSteps()[i] != -1){
                list.add(state.getWalkDirection().getSteps()[i]);
            }
        }
        return list;
    }

    public void setAdjacentCells(int[] adjacentCells) {
        this.adjacentCells = adjacentCells;
    }
}
