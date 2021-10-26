package gameobjects.units;

public enum WalkDirection {
    UPWARD(new int[]{0,2}),
    DOWNWARD(new int[]{5,7}),
    BIDIRECTIONAL(new int[]{0,2,5,7}),
    NONE(new int[]{});

    private final int[] steps;

    WalkDirection(int[] steps){
        this.steps = steps;
    }

    public int[] getSteps() {
        return steps;
    }
}
