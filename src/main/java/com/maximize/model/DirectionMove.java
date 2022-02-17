package com.maximize.model;

public enum DirectionMove {
    UP('U'), DOWN('D'), LEFT('L'), RIGHT('R');

    private final char value;

    DirectionMove(char value){
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
