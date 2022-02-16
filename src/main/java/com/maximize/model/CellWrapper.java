package com.maximize.model;

public enum CellWrapper {
    COLUMN('A'), HIDDEN('@'), SHOW('S'),
    SPLIT(',');

    private final char value;

    CellWrapper(char value){
        this.value = value;
    }

    public char getValue(){ return value; }
}
