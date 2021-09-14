package gameobjects.units.players;

import gameobjects.units.Unit;
import gameobjects.units.WalkDirection;

public class CustomModel extends Unit {

    /**
     * @param walkDirection
     * @return Возвращает может ли юнит пойти на указанную клетку
     */
    public CustomModel(WalkDirection walkDirection) {
        super(walkDirection);
    }
}
