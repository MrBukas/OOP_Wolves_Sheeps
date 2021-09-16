package gameengine;


import gameengine.position.Coordinate;

import java.util.Scanner;

public class CommandReader {
    Scanner scanner;

    public CommandReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Coordinate readCommand(CommandType commandType){
        if (commandType == CommandType.TEXT) return null;
        String command = scanner.nextLine();
        return new Coordinate(
                (command.charAt(0)),
                Integer.parseInt(String.valueOf(command.charAt(1)))
        );
    }
}
