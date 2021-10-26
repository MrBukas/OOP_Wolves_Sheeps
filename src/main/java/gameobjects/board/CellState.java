package gameobjects.board;

import gameobjects.units.WalkDirection;

public enum CellState {
    FREE(WalkDirection.NONE),
    SHEEP(WalkDirection.BIDIRECTIONAL),
    WOLF(WalkDirection.DOWNWARD);

    private final WalkDirection walkDirection;

    CellState(WalkDirection walkDirection){
        this.walkDirection = walkDirection;
    }

    public WalkDirection getWalkDirection() {
        return walkDirection;
    }
}
