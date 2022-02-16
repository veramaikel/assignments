package com.maximize.model;

public class Cell extends Position {
    private CellType type;
    private boolean hidden;

    public Cell(int row, int col){
        this(row, col, CellType.EMPTY, true);
    }

    Cell(int row, int col, CellType type){
        this(row, col, type, true);
    }

    Cell(int row, int col, CellType type, boolean hidden){
        super(row, col);
        this.type = type;
        this.hidden = hidden;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
