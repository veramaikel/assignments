package com.maximize.model;

public enum OrientationMove {
    HORIZONTAL('H'), VERTICAL('V'), BOTH('B');

    private final char value;

    OrientationMove(char value){
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
