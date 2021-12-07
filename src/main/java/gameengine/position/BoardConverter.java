package gameengine.position;

public class BoardConverter {
    public static int boardLetterToWidth(char letter){
        return Character.getNumericValue(letter) - 10;
    }

    public static int boardNumberToHeight(int number){
        return 8 - number;
    }

    public static String positionToString(int position){
        return String.valueOf(widthToBoardLetter(position % 8)) + (8 - (position / 8)) + " ";
    }
    public static char widthToBoardLetter(int width){
        return (char) ((char) width + 65);
    }

    public static int heightToBoardNumber(int height){
        return 8 - height;
    }
}
