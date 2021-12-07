package gameengine.position;

import static gameengine.position.BoardConverter.widthToBoardLetter;

public class Coordinate implements Comparable<Coordinate>{
    private char letter;
    private int number;

    /**
     * Хранит в себе координату в привычном виде (типа A1)
     * @param letter
     * @param number
     */
    public Coordinate(char letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public Coordinate(int index){
        letter = widthToBoardLetter(index % 8);
        number = 8 - (index / 8);
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

    public int toIndex(){
        return ((8 - number) * 8) + BoardConverter.boardLetterToWidth(letter);
    }


    public static char numberToChar(int coordinate){//Не факт что работает корректно
        return (char) (coordinate + 65);
    }

    @Override
    public int compareTo(Coordinate o) {
        return hashCode() - o.hashCode();
    }

    @Override
    public int hashCode() {
        return letter * 10 + number;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return letter + "" + number;
    }
}
