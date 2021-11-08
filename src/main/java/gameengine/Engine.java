package gameengine;

import gameengine.position.BoardConverter;
import gameengine.position.Coordinate;
import gameobjects.board.CellState;
import gameobjects.board2.Board;
import gameobjects.board2.BoardCell;
import gameobjects.board2.BoardMethods;

import java.io.File;
import java.util.List;

public class Engine {
    CommandReader commandReader;

    public Engine(CommandReader commandReader) {
        this.commandReader = commandReader;
    }

    public void startGame(){
        Board board = new Board(new File("src/main/resources/board1.txt"));

        boolean sheepTurn = true;

        while (true) {//Условие завершения игры
            if (sheepTurn){
                System.out.println("Ход ОВЦЫ");
            }else {
                System.out.println("Ход ВОЛКА");
            }
            BoardMethods.printBoard(board);
            System.out.println("Выберите юнит");
            Coordinate unitCoordinate = commandReader.readCommand(CommandType.COORDINATE);
            BoardCell selectedCell = board
                    .getCell(
                            BoardConverter.boardNumberToHeight(unitCoordinate.getNumber()),
                            BoardConverter.boardLetterToWidth(unitCoordinate.getLetter()));
            if (selectedCell.getState() == CellState.FREE){
                System.out.println("Вы выбрали пустую клетку");
                continue;
            }
            if (sheepTurn != (selectedCell.getState() == CellState.SHEEP)){
                System.out.println("Вы выбрали не своего юнита");
                continue;
            }
            System.out.println("Доступные шаги:");
            
            for (Integer cell: selectedCell.getAvailableCells()) {
                System.out.print(String.valueOf(widthToBoardLetter(cell % 8)) + (8 - (cell / 8)) + " ");
            }
            System.out.println();
            System.out.println("Выберите координату (куда пойти)");
            Coordinate endCoordinate = commandReader.readCommand(CommandType.COORDINATE);
            if (checkIfListContainsCoordinate(selectedCell.getAvailableCells(), endCoordinate.toIndex()))
                BoardMethods.moveUnit(board,unitCoordinate, endCoordinate);
            else{
                System.out.println("Нельзя пойти в выбранную клетку");
                continue;
            }
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

    private boolean checkIfListContainsCoordinate(List<Integer> coordinates, Integer target){
        for (Integer coordinate : coordinates) {
            if (coordinate.equals(target)) return true;
        }
        return false;
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
                    return gameBoard[i][j].getAvailableCells().size() == 0;
                }
            }
        }
        return false;
    }
    private char widthToBoardLetter(int width){
        return (char) ((char) width + 65);
    }



}
