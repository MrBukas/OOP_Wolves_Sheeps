package gameengine;

import gameengine.position.BoardConverter;
import gameengine.position.Coordinate;
import gameobjects.board.Board;
import gameobjects.board.BoardCell;
import gameobjects.board.BoardMethods;
import gameobjects.units.Unit;
import gameobjects.units.UnitMethods;

import java.io.File;
import java.util.List;

public class Engine {
    CommandReader commandReader;

    public Engine(CommandReader commandReader) {
        this.commandReader = commandReader;
    }

    public void startGame(){
        Board board = new Board(new File("src/main/resources/board1.txt"));//TODO Получать файл
        BoardMethods.printBoard(board);

        for (int i = 0; i < 10; i++) {//Условие завершения игры
            System.out.println("Выберите юнит");
            Coordinate unitCoordinate = commandReader.readCommand(CommandType.COORDINATE);
            Unit selectedUnit = board
                    .getCell(
                            BoardConverter.boardNumberToHeight(unitCoordinate.getNumber()),
                            BoardConverter.boardLetterToWidth(unitCoordinate.getLetter()))
                    .getUnit();
            if (selectedUnit == null){
                System.out.println("Вы выбрали пустую клетку");
                continue;
            }
            System.out.println("Доступные шаги:");
            List<BoardCell> availableCells = UnitMethods.getAvailableSteps(
                    board,
                    selectedUnit,
                    BoardConverter.boardLetterToWidth(unitCoordinate.getLetter()),
                    BoardConverter.boardNumberToHeight(unitCoordinate.getNumber()));
            for (BoardCell cell: availableCells) {
                System.out.print(cell.getLetterCoordinate() + "" + cell.getNumberCoordinate() + " ");
            }
            System.out.println();
            System.out.println("Выберите координату (куда пойти)");
            Coordinate endCoordinate = commandReader.readCommand(CommandType.COORDINATE);
            BoardMethods.moveUnit(board,unitCoordinate,endCoordinate);
        }
    }

}
