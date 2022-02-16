package com.dixcover.model;

public enum OrientationMove {
    HORIZONTAL('R'), VERTICAL('C'), BOTH('B');

    private final char value;

    OrientationMove(char value){
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
