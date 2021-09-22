package gameengine;

import gameengine.position.BoardConverter;
import gameengine.position.Coordinate;
import gameobjects.board.Board;
import gameobjects.board.BoardCell;
import gameobjects.board.BoardMethods;
import gameobjects.board.CellState;
import gameobjects.units.Unit;
import gameobjects.units.UnitMethods;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Engine {
    CommandReader commandReader;

    public Engine(CommandReader commandReader) {
        this.commandReader = commandReader;
    }

    public void startGame(){
        Board board = new Board(new File("src/main/resources/board1.txt"));//TODO Получать файл
        BoardMethods.printBoard(board);
        boolean sheepTurn = true;

        while (true) {//Условие завершения игры
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
            List<Coordinate> availableCells = UnitMethods.getAvailableSteps(
                    board,
                    selectedUnit,
                    BoardConverter.boardLetterToWidth(unitCoordinate.getLetter()),
                    BoardConverter.boardNumberToHeight(unitCoordinate.getNumber()));
            for (Coordinate cell: availableCells) {
                System.out.print(cell + " ");
            }
            System.out.println();
            System.out.println("Выберите координату (куда пойти)");
            Coordinate endCoordinate = commandReader.readCommand(CommandType.COORDINATE);
            if (availableCells.contains(endCoordinate))
                BoardMethods.moveUnit(board,unitCoordinate,endCoordinate);
            else
                System.out.println("Нельзя пойти в выбранную клетку");

            if (checkSheepWin(board)) {
                System.out.println("Овца выиграла");
                return;
            }
            if (checkWolfWin(board)) {
                System.out.println("Волк выиграл");
                return;
            }
            sheepTurn = !sheepTurn;
        }
    }

    private boolean checkSheepWin(Board board){
        BoardCell[][] gameBoard = board.getGameBoard();
        for (int j = 0; j < gameBoard[0].length; j++) {
            if (gameBoard[0][j].getState() == CellState.SHEEP) return true;
        }
        return false;
    }

    private boolean checkWolfWin(Board board){
        BoardCell[][] gameBoard = board.getGameBoard();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j].getState() == CellState.SHEEP){
                    List<Coordinate> coordinates = UnitMethods.getAvailableSteps(
                            board,
                            gameBoard[i][j].getUnit(),
                            j,
                            i);

                    return coordinates.size() == 0;
                }
            }
        }
        return false;
    }

}
