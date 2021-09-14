import gameengine.CommandReader;
import gameengine.Engine;
import gameobjects.board.Board;
import gameobjects.board.BoardMethods;
import gameobjects.board.CellColor;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandReader commandReader = new CommandReader(
                new Scanner(System.in)
        );
        Engine engine = new Engine(commandReader);



        engine.startGame();
    }
}
