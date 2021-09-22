import gameengine.CommandReader;
import gameengine.CommandType;
import gameengine.position.BoardConverter;
import gameengine.position.Coordinate;
import gameengine.Engine;

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
