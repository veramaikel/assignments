package com.maximize.model;

public enum SenseMove {
    FORWARD('F'), BACKWARD('K'), BOTH('B');

    private final char value;

    SenseMove(char value){
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
