package com.dixcover.model;

public enum CellWrapper {
    ROW('A'), COLUMN('B'),
    HIDDEN('C'), SHOW('D'),
    SPLIT(',');

    private final char value;

    CellWrapper(char value){
        this.value = value;
    }

    public char getValue(){ return value; }
}
