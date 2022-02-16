package com.maximize.model;

public enum CellType {
    EMPTY('.'), ZERO('X'),
    POINT('O'), PLUS('+'),
    DUPLEX('*'), STOP('#');

    private final char value;

    CellType(char value){
        this.value = value;
    }

    public char getValue(){
        return value;
    }
}
