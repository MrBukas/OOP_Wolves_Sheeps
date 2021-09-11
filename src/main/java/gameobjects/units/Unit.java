package gameobjects.units;

import gameobjects.board.Board;
import gameobjects.board.BoardCell;
import gameobjects.board.BoardMethods;

import java.util.Arrays;

public abstract class Unit{
    WalkDirection walkDirection;
    /**
     * @return Возвращает может ли юнит пойти на указанную клетку
     */
    public boolean checkIfCanWalk(Board board, BoardCell cell) {
        return  Arrays.asList(getAvailableSteps(
                board,
                cell.getLetterCoordinate(),
                cell.getNumberCoordinate())).contains(cell);
    }
    /**
     * @param board Доска на которой находится юнит
     * @return Возвращает массив BoardCell на которые
     * может походить юнит
     */
    public BoardCell[] getAvailableSteps(Board board, char letterCoordinate, int numberCoordinate){
        return null;
    }

    public Unit(WalkDirection walkDirection) {
        this.walkDirection = walkDirection;
    }
}
