package gameengine.position;

public class Coordinate {
    private char letter;
    private int number;

    public Coordinate(char letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public static char numberToChar(int coordinate){//Не факт что работает корректно
        return (char) (coordinate + 65);
    }
}
