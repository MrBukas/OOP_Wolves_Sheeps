package gameobjects.units;

import gameobjects.board.Board;
import gameobjects.board.BoardCell;
import gameobjects.board.BoardMethods;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static gameengine.Coordinate.letterToNumber;

public class UnitMethods {

    /**
     * @param board Доска на которой находится юнит
     * @return Возвращает массив BoardCell на которые
     * может походить юнит
     */
    public static List<BoardCell> getAvailableSteps(Board board, Unit unit, char letterCoordinate, int numberCoordinate){
        int heightCoordinate = letterToNumber(letterCoordinate);
        List<BoardCell> possibleMoves = new LinkedList<BoardCell>();
        switch (unit.getWalkDirection()){
            case UPWARD:
                if ((heightCoordinate < board.getHeight()) && (numberCoordinate + 1 < board.getWidth())){
                    possibleMoves.add(board.getCell(heightCoordinate, numberCoordinate));
                }
                if ((heightCoordinate < board.getHeight()) && (numberCoordinate - 1 >= 0)){
                    possibleMoves.add(board.getCell(heightCoordinate, numberCoordinate));
                }
                break;
            case BIDIRECTIONAL:
                if ((heightCoordinate < board.getHeight()) && (numberCoordinate + 1 < board.getWidth())){
                possibleMoves.add(board.getCell(heightCoordinate, numberCoordinate));
                }
                if ((heightCoordinate < board.getHeight()) && (numberCoordinate - 1 >= 0)){
                    possibleMoves.add(board.getCell(heightCoordinate, numberCoordinate));
                }
                if ((heightCoordinate >= 0) && (numberCoordinate + 1 < board.getWidth())){
                    possibleMoves.add(board.getCell(heightCoordinate, numberCoordinate));
                }
                if ((heightCoordinate >= 0) && (numberCoordinate - 1 >= 0)){
                    possibleMoves.add(board.getCell(heightCoordinate, numberCoordinate));
                }
                break;
            case DOWNWARD:
                if ((heightCoordinate >= 0) && (numberCoordinate + 1 < board.getWidth())){
                    possibleMoves.add(board.getCell(heightCoordinate, numberCoordinate));
                }
                if ((heightCoordinate >= 0) && (numberCoordinate - 1 >= 0)){
                    possibleMoves.add(board.getCell(heightCoordinate, numberCoordinate));
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
