import gameengine.CommandReader;
import gameengine.CommandType;
import gameengine.position.BoardConverter;
import gameengine.position.Coordinate;
import gameengine.Engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CommandReader commandReader = new CommandReader(
                new Scanner(new File("src/main/resources/recordedgames/game1.txt"))//System.in
        );
        Engine engine = new Engine(commandReader);
        engine.startGame();
    }
}
