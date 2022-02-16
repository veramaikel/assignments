package com.dixcover.model;

import com.dixcover.util.DixcoverDeque;
import org.jetbrains.annotations.NotNull;

public class Board {
    private Integer id;
    private Cell[][] matrix;
    private int rows, columns;
    private DixcoverDeque<Cell[][]> changes;
    private static boolean saved;

    public Board(int rows, int columns){ this(rows, columns, null, null); }

    public Board(int rows, int columns, Integer id, String matrixTex){
        this.rows = rows;
        this.columns = columns;
        this.id = id;
        changes = new DixcoverDeque<Cell[][]>();
        if(matrixTex==null){
            this.matrix = new Cell[rows][columns];
            generate();
            this.saved = false;
        }
        else{
            wrapperMatrix(matrixTex);
        }
    }

    private void wrapperMatrix(@NotNull String matrixTex) {
        String[] wrows = matrixTex.split(String.valueOf(CellWrapper.SPLIT.getValue()));
        if(wrows.length == this.rows) {
            for (int i = 0; i < wrows.length; i++) {
                String[] wcolumns = wrows[i].split(String.valueOf(CellWrapper.COLUMN.getValue()));
                if(wcolumns.length == this.columns) {
                    for (int j = 0; j < wcolumns.length; j++) {
                        String wcell = wcolumns[j];
                        char status = wcell.charAt(0);
                        char type = wcell.charAt(1);
                        Cell cell = new Cell(i, j);
                        if(CellWrapper.SHOW.getValue() == status) {
                            cell.setHidden(false);
                        }
                        if(CellType.OBSTACLE.getValue() == type){
                            cell.setType(CellType.OBSTACLE);
                        }
                        else if(CellType.SUPERBOMB.getValue() == type){
                            cell.setType(CellType.SUPERBOMB);
                        }
                        else if(CellType.BOMB.getValue() == type){
                            cell.setType(CellType.BOMB);
                        }
                        else if(CellType.TARGET.getValue() == type){
                            cell.setType(CellType.TARGET);
                        }
                        else if(CellType.PLAYAGAIN.getValue() == type){
                            cell.setType(CellType.PLAYAGAIN);
                        }

                        this.matrix[i][j] = cell;
                    }
                }//Qué hacer si wcolumns != columns
            }
        }//Qué hacer si wrows != rows
    }

    public String wrapperMatrix() {
        StringBuilder wmatrix = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            if(i>0) wmatrix.append(CellWrapper.SPLIT.getValue());
            for (int j = 0; j < columns; j++) {
                wmatrix.append(CellWrapper.COLUMN.getValue());
                Cell cell = this.matrix[i][j];
                if(cell.isHidden()) wmatrix.append(CellWrapper.HIDDEN.getValue());
                else wmatrix.append(CellWrapper.SHOW.getValue());
                wmatrix.append(cell.getType().getValue());
            }
        }

        return wmatrix.toString();
    }

    private void generate() {

    }

    public void print() {

    }

    public void play(Move move){
        changes.push(this.matrix);
        //Procesar la jugada
        this.saved = false;
    }

    public void reverse(){
        if(!changes.empty()) { this.matrix = changes.pop();}
        this.saved = false;
    }
}
