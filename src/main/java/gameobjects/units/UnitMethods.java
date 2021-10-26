package gameobjects.units;

import gameengine.position.BoardConverter;
import gameengine.position.Coordinate;
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
    public static List<gameobjects.board2.BoardCell> getSteps(gameobjects.board2.Board board, gameobjects.board2.BoardCell cell){
        List<gameobjects.board2.BoardCell> list = new LinkedList<>();
        for (Integer index: cell.getAvailableCells()) {
            list.add(board.getCell(index));
        }
        return list;
    }

    public static List<Coordinate> getAvailableSteps(Board board, Unit unit, int widthCoordinate, int heightCoordinate){
        List<Coordinate> possibleMoves = new LinkedList<>();
        switch (unit.getWalkDirection()){
            case UPWARD:
                if ((heightCoordinate > 0) && (widthCoordinate + 1 < board.getWidth() - 1)){ //Вправо вверх
                    possibleMoves.add(new Coordinate(
                            BoardConverter.widthToBoardLetter(widthCoordinate + 1),
                            BoardConverter.heightToBoardNumber(heightCoordinate - 1)));
                }
                if ((heightCoordinate > 0) && (widthCoordinate > 0)){ //Влево вверх
                    possibleMoves.add(new Coordinate(
                            BoardConverter.widthToBoardLetter(widthCoordinate - 1),
                            BoardConverter.heightToBoardNumber(heightCoordinate - 1)));
                }
                break;
            case BIDIRECTIONAL:
                if ((heightCoordinate > 0) && (widthCoordinate + 1 < board.getWidth() - 1)){ //Вправо вверх
                    possibleMoves.add(new Coordinate(
                            BoardConverter.widthToBoardLetter(widthCoordinate + 1),
                            BoardConverter.heightToBoardNumber(heightCoordinate - 1)));
                }
                if ((heightCoordinate > 0) && (widthCoordinate > 0)){ //Влево вверх
                    possibleMoves.add(new Coordinate(
                            BoardConverter.widthToBoardLetter(widthCoordinate - 1),
                            BoardConverter.heightToBoardNumber(heightCoordinate - 1)));
                }
                if ((heightCoordinate + 1 < board.getHeight() - 1) && (widthCoordinate + 1 < board.getWidth() - 1)){ //Вправо вниз
                   possibleMoves.add(new Coordinate(
                           BoardConverter.widthToBoardLetter(widthCoordinate + 1),
                           BoardConverter.heightToBoardNumber(heightCoordinate + 1)));
               }
               if ((heightCoordinate + 1 < board.getHeight() - 1) && (widthCoordinate > 0)){ //Влево вниз
                   possibleMoves.add(new Coordinate(
                           BoardConverter.widthToBoardLetter(widthCoordinate - 1),
                           BoardConverter.heightToBoardNumber(heightCoordinate + 1)));
               }
                break;
            case DOWNWARD:
                if ((heightCoordinate + 1 < board.getHeight() - 1) && (widthCoordinate + 1 < board.getWidth() - 1)){ //Вправо вниз
                    possibleMoves.add(new Coordinate(
                            BoardConverter.widthToBoardLetter(widthCoordinate + 1),
                            BoardConverter.heightToBoardNumber(heightCoordinate + 1)));
                }
                if ((heightCoordinate + 1 < board.getHeight() - 1) && (widthCoordinate > 0)){ //Влево вниз
                    possibleMoves.add(new Coordinate(
                            BoardConverter.widthToBoardLetter(widthCoordinate - 1),
                            BoardConverter.heightToBoardNumber(heightCoordinate + 1)));
                }
        }

        for (int i = 0; i < possibleMoves.size(); i++) {
            Coordinate coordinate = possibleMoves.get(i);
            if (board.getCell(
                    BoardConverter.boardNumberToHeight(coordinate.getNumber()),
                    BoardConverter.boardLetterToWidth(coordinate.getLetter())).getUnit() != null
            ){
                possibleMoves.remove(i);
                i--;
            }
        }
        return possibleMoves;
    }
    //....

    public static boolean checkIfCanWalk(Board board, BoardCell cell, Unit unit) {
        return  getAvailableSteps(
                board,
                unit,
                cell.getLetterCoordinate(),
                cell.getNumberCoordinate()).contains(cell);
    }


}
