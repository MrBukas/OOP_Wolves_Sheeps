package gameobjects.units;

import gameobjects.board.Board;
import gameobjects.board.BoardCell;
import gameobjects.board.BoardMethods;

import java.util.Arrays;

public abstract class Unit{
    protected WalkDirection walkDirection;
    /**
     * @return Возвращает может ли юнит пойти на указанную клетку
     */



    public Unit(WalkDirection walkDirection) {
        this.walkDirection = walkDirection;
    }

    public WalkDirection getWalkDirection() {
        return walkDirection;
    }
}
