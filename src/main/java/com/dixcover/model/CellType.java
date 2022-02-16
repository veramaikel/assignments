package com.dixcover.model;

public enum CellType {
    EMPTY('.'), TARGET('X'),
    OBSTACLE('O'), PLAYAGAIN('+'),
    BOMB('*'), SUPERBOMB('#');

    private char value;

    private CellType(char value){
        this.value = value;
    }

    public char getValue(){
        return value;
    }
}
