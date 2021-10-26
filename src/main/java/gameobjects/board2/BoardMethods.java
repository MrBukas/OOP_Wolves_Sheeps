package gameobjects.board2;

import gameobjects.board.CellColor;
import gameobjects.board.CellState;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardMethods {
    final static int size = 8;
    public static BoardCell[] readBoard(File boardData){
        Scanner scanner = null;
        CellColor[] colors = CellColor.values();
        int colorCount = colors.length;
        int currentColor = 1;
        try {
            scanner = new Scanner(boardData);
        } catch (FileNotFoundException e) {
            return null;
        }
        BoardCell[] cells = new BoardCell[size * size];
        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < size; j++) {
                currentColor = (currentColor + 1) % colorCount;
                CellState state = null;
                char cellChar = line.charAt(j);
                switch (cellChar){
                    case '.': state = CellState.FREE; break;
                    case 'W': state = CellState.WOLF; break;
                    case 'S': state = CellState.SHEEP; break;
                }
                int index = j + 8*i;
                cells[index] = new BoardCell(state, colors[currentColor]);
                cells[index].setAdjacentCells(setAdjacentCells(index));
            }
            currentColor--;
        }
        return null;
    }

    private static int[] setAdjacentCells(int id){
        int[] difs = {-9,-8,-7,-1,1,7,8,9};
        int[] adj = new int[8];
        for (int i = 0; i < 8; i++) {
            adj[i] = id + difs[i];
        }
//        Правый борт
        if (id % 8 == 7){
            adj[2] = -1;
            adj[4] = -1;
            adj[7] = -1;
        }
//        Левый борт
        if (id % 8 == 0){
            adj[0] = -1;
            adj[3] = -1;
            adj[5] = -1;
        }
//        Верхний борт
        if (id < 8){
            adj[0] = -1;
            adj[1] = -1;
            adj[2] = -1;
        }
//        Нижний борт
        if (id > 55){
            adj[5] = -1;
            adj[6] = -1;
            adj[7] = -1;
        }
        return adj;
    }

    public static void main(String[] args) {
        readBoard(new File("src/main/resources/board1.txt"));
    }
}
