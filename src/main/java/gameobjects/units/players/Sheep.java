package gameobjects.units.players;

import gameobjects.board.Board;
import gameobjects.board.BoardCell;
import gameobjects.board.BoardMethods;
import gameobjects.units.Unit;
import gameobjects.units.WalkDirection;

public class Sheep extends Unit {
    public Sheep(){
        super(WalkDirection.BIDIRECTIONAL);
    }
}
