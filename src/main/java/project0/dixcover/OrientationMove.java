package project0.dixcover;

public enum OrientationMove {
    HORIZONTAL('R'), VERTICAL('C'), BOTH('B');

    private char value;

    OrientationMove(char value){
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
