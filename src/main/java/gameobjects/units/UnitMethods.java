package gameobjects.units;

import gameengine.position.BoardConverter;
import gameobjects.board.Board;
import gameobjects.board.BoardCell;

import java.util.LinkedList;
import java.util.List;


public class UnitMethods {

    /**
     * @param board Доска на которой находится юнит
     * @return Возвращает массив BoardCell на которые
     * может походить юнит
     */
    public static List<BoardCell> getAvailableSteps(Board board, Unit unit, int widthCoordinate, int heightCoordinate){
        List<BoardCell> possibleMoves = new LinkedList<BoardCell>();
        switch (unit.getWalkDirection()){
            case UPWARD:
                if ((heightCoordinate < board.getHeight()) && (widthCoordinate + 1 < board.getWidth())){
                    possibleMoves.add(board.getCell(heightCoordinate, widthCoordinate));
                }
                if ((heightCoordinate < board.getHeight()) && (widthCoordinate - 1 >= 0)){
                    possibleMoves.add(board.getCell(heightCoordinate, widthCoordinate));
                }
                break;
            case BIDIRECTIONAL:
                if ((heightCoordinate < board.getHeight()) && (widthCoordinate + 1 < board.getWidth())){
                possibleMoves.add(board.getCell(heightCoordinate, widthCoordinate));
                }
                if ((heightCoordinate < board.getHeight()) && (widthCoordinate - 1 >= 0)){
                    possibleMoves.add(board.getCell(heightCoordinate, widthCoordinate));
                }
                if ((heightCoordinate >= 0) && (widthCoordinate + 1 < board.getWidth())){
                    possibleMoves.add(board.getCell(heightCoordinate, widthCoordinate));
                }
                if ((heightCoordinate >= 0) && (widthCoordinate - 1 >= 0)){
                    possibleMoves.add(board.getCell(heightCoordinate, widthCoordinate));
                }
                break;
            case DOWNWARD:
                if ((heightCoordinate >= 0) && (widthCoordinate + 1 < board.getWidth())){
                    possibleMoves.add(board.getCell(heightCoordinate, widthCoordinate));
                }
                if ((heightCoordinate >= 0) && (widthCoordinate - 1 >= 0)){
                    possibleMoves.add(board.getCell(heightCoordinate, widthCoordinate));
                }break;
        }

        return possibleMoves;
    }

    public static boolean checkIfCanWalk(Board board, BoardCell cell, Unit unit) {
        return  getAvailableSteps(
                board,
                unit,
                cell.getLetterCoordinate(),
                cell.getNumberCoordinate()).contains(cell);
    }


}
