package gameengine;

import gameengine.position.BoardConverter;
import gameengine.position.Coordinate;
import gameobjects.board.CellState;
import gameobjects.board2.Board;
import gameobjects.board2.BoardCell;
import gameobjects.board2.BoardMethods;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Engine {
    CommandReader commandReader;
    int stepCount = 0;

    public Engine(CommandReader commandReader) {
        this.commandReader = commandReader;
    }

    public void startGame(){
        Board board = new Board(new File("src/main/resources/board1.txt"));
        Random randomizer = new Random();

        Set<Coordinate> triedSteps = new HashSet<>();

        boolean sheepTurn = true;

        List<Integer> availableMoves;
        while (true) {//Условие завершения игры
            stepCount++;
            if (stepCount == 500){
                System.out.println("Loop");
            }
            if (sheepTurn){
                System.out.println("Ход ОВЦЫ");
                availableMoves = board.getSheepCell().stream().map(BoardCell::getId).collect(Collectors.toList());
            }else {
                System.out.println("Ход ВОЛКА");
                triedSteps.clear();
                availableMoves = board.getWolfCells().stream().map(BoardCell::getId).collect(Collectors.toList());
                //волки
                boolean flag = false;
                for (int wolf = 0; wolf < 4; wolf++) {
                    Coordinate unitCoordinate = new Coordinate(availableMoves.get(wolf));
                    List<Integer> availableCells = board
                            .getCell(
                                    BoardConverter.boardNumberToHeight(unitCoordinate.getNumber()),
                                    BoardConverter.boardLetterToWidth(unitCoordinate.getLetter())).getAvailableCells();
                    if ( availableCells.size() > 0){
                        for (Integer cell: availableCells) {
                            if (board.getCell(cell).getState() == CellState.FREE){
                                flag = true;
                            }
                        }
                    }
                    //board.getCell(endCoordinate.toIndex()).getState() == CellState.FREE

                }
                if (!flag) {
                    System.out.println("Овца выиграла");
                    return;
                }
            }
            BoardMethods.printBoard(board);
            System.out.println("Выберите юнит");
            //Coordinate unitCoordinate = commandReader.readCommand(CommandType.COORDINATE);
            Coordinate unitCoordinate = new Coordinate(availableMoves.get(randomizer.nextInt(availableMoves.size())));
            System.out.println();
            System.out.println(unitCoordinate);
            System.out.println();

            BoardCell selectedCell = board
                    .getCell(
                            BoardConverter.boardNumberToHeight(unitCoordinate.getNumber()),
                            BoardConverter.boardLetterToWidth(unitCoordinate.getLetter()));
            while (selectedCell.getAvailableCells().size() == 0){
                unitCoordinate = new Coordinate(availableMoves.get(randomizer.nextInt(availableMoves.size())));
                selectedCell = board
                        .getCell(
                                BoardConverter.boardNumberToHeight(unitCoordinate.getNumber()),
                                BoardConverter.boardLetterToWidth(unitCoordinate.getLetter()));
            }
            if (selectedCell.getState() == CellState.FREE){
                System.out.println("Вы выбрали пустую клетку");
                continue;
            }
            if (sheepTurn != (selectedCell.getState() == CellState.SHEEP)){
                System.out.println("Вы выбрали не своего юнита");
                continue;
            }
            System.out.println("Доступные шаги:");

            availableMoves = selectedCell.getAvailableCells();
            for (Integer cell: availableMoves) {
                System.out.print(BoardConverter.positionToString(cell));
            }
            System.out.println();
            System.out.println("Выберите координату (куда пойти)");
            //Чтение если бы мы ходили сами
            //Coordinate endCoordinate = commandReader.readCommand(CommandType.COORDINATE);
            Coordinate endCoordinate = null;
            while (endCoordinate == null) {
                try {
                    endCoordinate = new Coordinate(availableMoves.get(randomizer.nextInt(availableMoves.size())));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


            System.out.println();
            System.out.println(endCoordinate);
            System.out.println();
            if (checkIfListContainsCoordinate(selectedCell.getAvailableCells(), endCoordinate.toIndex())
                    && board.getCell(endCoordinate.toIndex()).getState() == CellState.FREE)
                BoardMethods.moveUnit(board,unitCoordinate, endCoordinate);
            else{
                System.out.println("Нельзя пойти в выбранную клетку");
                if(sheepTurn){
                    triedSteps.add(endCoordinate);
                    if (triedSteps.size() == selectedCell.getAvailableCells().size()){
                        System.out.println("Волк выиграл(овце некуда идти)");
                        return;
                    }
                }
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
        for (int j = 0; j < BoardMethods.size; j++) {
            if (board.getCell(j).getState() == CellState.SHEEP) return true;
        }
        return false;
    }

    private boolean checkWolfWin(Board board){
        return board.getSheepCell().get(0).getAvailableCells().size() == 0;
    }
    private char widthToBoardLetter(int width){
        return (char) ((char) width + 65);
    }



}
