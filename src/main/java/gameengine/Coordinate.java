package gameengine;

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
    public static int letterToNumber(char letter){
        return Character.getNumericValue(letter) - 9;
    }

    public static char numberToChar(int coordinate){//Не работает
        return (char) (coordinate + 9);//TODO Подставить значение буквы из числа
    }
}
