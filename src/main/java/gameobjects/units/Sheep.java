package gameobjects.units;

import gameobjects.board.Board;
import gameobjects.board.BoardCell;
import gameobjects.board.BoardMethods;

public class Sheep extends Unit{
    public Sheep(WalkDirection walkDirection) {
        super(walkDirection);
    }

    @Override
    public BoardCell[] getAvailableSteps(Board board, char letterCoordinate, int numberCoordinate) {
        BoardCell[][] cells = board.getGameBoard();
        int x = numberCoordinate;
        int y = BoardMethods.letterToNumber(letterCoordinate);

return null;
    }
}
