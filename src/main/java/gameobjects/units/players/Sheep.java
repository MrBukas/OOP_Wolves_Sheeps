package gameobjects.units.players;

import gameobjects.units.Unit;
import gameobjects.units.WalkDirection;

public class Sheep extends Unit {
    public Sheep(){
        super(WalkDirection.BIDIRECTIONAL);
    }
}
