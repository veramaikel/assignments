package com.maximize.model;

import com.maximize.util.MaximizeStack;
import org.apache.log4j.Logger;

public class Board {
    private static final Logger log = Logger.getLogger(Board.class);
    public static final int MAX_DIM = 50;
    private Integer id;
    private Cell[][] matrix;
    private int rows, columns;
    private MaximizeStack<Cell[][]> changes;
    private static boolean hasChanged;
    private int[] hiddenR;
    private int[] hiddenC;
    private int positiveCells;

    public Board(int rows, int columns){ this(rows, columns, null, null); }

    public Board(int id, String matrixTex){ this(0, 0, id, matrixTex); }

    private Board(int rows, int columns, Integer id, String matrixTex){
        this.rows = Math.min(rows, MAX_DIM);
        this.columns = Math.min(columns, MAX_DIM);
        this.id = id;
        this.changes = new MaximizeStack<>();
        this.hiddenC = new int[this.columns];
        this.hiddenR = new int[this.rows];
        this.positiveCells = 0;
        if(matrixTex==null){
            this.matrix = new Cell[this.rows][this.columns];
            generate();
        }
        else{
            unwrapper(matrixTex);
        }
        hasChanged = false;
    }

    private void unwrapper(String matrixTex) {
        String[] wrows = matrixTex.split(String.valueOf(CellWrapper.SPLIT.getValue()));
        this.rows = wrows.length;
        for (int i = 0; i < wrows.length; i++) {
            String[] wcolumns = wrows[i].split(String.valueOf(CellWrapper.COLUMN.getValue()));
            if(i==0) {
                this.columns = wcolumns.length;
                this.matrix = new Cell[this.rows][this.columns];
                this.hiddenC = new int[this.columns];
                this.hiddenR = new int[this.rows];
            }
            this.hiddenR[i] = 0;
            for (int j = 0; j < wcolumns.length; j++) {
                if(i==0) this.hiddenC[j] = 0;
                String wcell = wcolumns[j];
                char status = wcell.charAt(0);
                char type = wcell.charAt(1);
                Cell cell = new Cell(i, j);
                boolean isHidden = true;
                if (CellWrapper.SHOW.getValue() == status) {
                    cell.setHidden(false);
                    isHidden = false;
                }
                else{
                    this.hiddenC[j]++;
                    this.hiddenR[i]++;
                }
                if (CellType.POINT.getValue() == type) {
                    cell.setType(CellType.POINT);
                    if(isHidden) this.positiveCells++;
                } else if (CellType.STOP.getValue() == type) {
                    cell.setType(CellType.STOP);
                } else if (CellType.DUPLEX.getValue() == type) {
                    cell.setType(CellType.DUPLEX);
                    if(isHidden) this.positiveCells++;
                } else if (CellType.ZERO.getValue() == type) {
                    cell.setType(CellType.ZERO);
                } else if (CellType.PLUS.getValue() == type) {
                    cell.setType(CellType.PLUS);
                }

                this.matrix[i][j] = cell;
            }
        }
    }

    public String wrapper() {
        StringBuilder wmatrix = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            if(i>0) wmatrix.append(CellWrapper.SPLIT.getValue());
            for (int j = 0; j < columns; j++) {
                if(j>0) wmatrix.append(CellWrapper.COLUMN.getValue());
                Cell cell = this.matrix[i][j];
                if(cell.isHidden()) wmatrix.append(CellWrapper.HIDDEN.getValue());
                else wmatrix.append(CellWrapper.SHOW.getValue());
                wmatrix.append(cell.getType().getValue());
            }
        }

        return wmatrix.toString();
    }

    private void generate() {
        int total = rows*columns;
        int points = total*30/100;
        int duplex = total*10/100;
        int stops = total*7/100;
        int plus = total*10/100;
        int zero = Game.MAX_PLAYERS;
        int empty = total - (points+duplex+stops+plus+zero);

        int[] cells = new int[total];
        for (int i = 0; i < total; i++) {
            cells[i] = 1;
        }

        int[] density = {empty/8, points/3, 0, zero, 0, duplex, 0, 0, 0, stops, 0, plus, 0, 0, 0};
        empty = empty - density[0];
        points = points - density[1];
        density[2] = empty/7;
        empty = empty - density[2];
        density[4] = empty/6;
        empty = empty - density[4];
        density[6] = empty/5;
        empty = empty - density[6];
        density[7] = points/2;
        density[8] = empty/4;
        empty = empty - density[8];
        density[10] = empty/3;
        empty = empty - density[10];
        density[12] = empty/2;
        density[13] = points - density[7];
        density[14] = empty - density[12];

        CellType[] types = {CellType.EMPTY, CellType.POINT, CellType.EMPTY, CellType.ZERO,
                CellType.EMPTY, CellType.DUPLEX, CellType.EMPTY, CellType.POINT, CellType.EMPTY,
                CellType.STOP, CellType.EMPTY, CellType.PLUS, CellType.EMPTY, CellType.POINT, CellType.EMPTY };

        for (int i = 0; i < Math.max(this.rows, this.columns); i++) {
            if(i<this.rows) this.hiddenR[i] = 0;
            if(i<this.columns) this.hiddenC[i] = 0;
        }
        for (int i = 0; i < total; i++) {
            int dindex, cindex;
            do {
                cindex = (int) (Math.random()*total); //rand.nextInt(cells.length);
                dindex = (int) (Math.random()*density.length); //rand.nextInt(density.length);
            }
            while(density[dindex]==0 || cells[cindex]==0);
            int r = cindex/columns;
            int c = cindex%columns;
            Cell cell = new Cell(r, c);
            cell.setType(types[dindex]);
            if(cell.getType().equals(CellType.DUPLEX) || cell.getType().equals(CellType.POINT))
                this.positiveCells++;
            density[dindex] = density[dindex] - 1;
            cells[cindex] = 0;
            matrix[r][c] = cell;
            this.hiddenC[c]++;
            this.hiddenR[r]++;
        }
    }

    public void print(boolean showAll) {
        StringBuilder s = new StringBuilder("\n\t");
        for (int i = -1; i < rows; i++) {

            for (int j = -1; j < columns; j++) {
                if(i == -1){
                    if(j == -1) s.append("y ");
                    else s.append("  ");
                }
                else {
                    if(j == -1){
                        s.append(i+1).append("   ");
                        if(rows>9 && i<9) s.append(" ");
                    }
                    else {
                        if (j >= 9) s.append(" ");
                        Cell cell = this.matrix[i][j];
                        if (showAll || !cell.isHidden()) s.append(cell.getType().getValue()).append(" ");
                        else s.append(CellWrapper.HIDDEN.getValue()).append(" ");
                    }
                }
            }
            s.append("\n\t");
        }
        s.append("\n\t");
        if(rows>9) s.append(" ");
        for (int i = -1; i <= columns; i++) {
            if(i==-1) s.append("    ");
            else if(i==columns) s.append(" x");
            else s.append(i+1).append(" ");
        }
        System.out.println(s);
    }

    public void play(Move move){
        path(move);
        if(hasChanged){
            changes.push(this.matrix);
            hasChanged = false;
        }
    }

    private void path(Move move) {
        if(isInside(move)) {
            Cell cell = this.matrix[move.getRow()][move.getColumn()];
            CellType type = cell.getType();
            boolean hidden = cell.isHidden();
            if(hidden) {
                this.hiddenC[cell.getColumn()]--;
                this.hiddenR[cell.getRow()]--;
            }
            cell.setHidden(false);
            this.matrix[move.getRow()][move.getColumn()] = cell;
            hasChanged = true;
            Player P = move.getPlayer();
            if (!type.equals(CellType.STOP)) {
                P.addMove(move);
                if (hidden) {
                    if (type.equals(CellType.POINT)){
                        P.addPoints(1);
                        this.positiveCells--;
                    }
                    else if (type.equals(CellType.DUPLEX)){
                        P.duplexPoints();
                        this.positiveCells--;
                    }
                    else if (type.equals(CellType.ZERO)) P.resetPoints();
                    else if (type.equals(CellType.PLUS)){
                        path(move.turn90());
                        path(move.turn270());
                    }
                }
                move.setPlayer(P);
                path(move.ahead());
            }
        }
        else {
            log.debug("Invalid Move out of Board: "+move);
        }
    }

    public boolean isInside(Move move){
        return (move.getRow()<rows && move.getRow()>=0 && move.getColumn()<columns && move.getColumn()>=0);
    }


    public void reverse(){
        if(!changes.empty()) { this.matrix = changes.pop();}
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getHiddenRow(int row) {
        return hiddenR[row];
    }

    public int getHiddenTotal() {
        int total = 0;
        for (int h : hiddenR){
            total = total + h;
        }
        return total;
    }

    public int getHiddenColumn(int column) {
        return hiddenC[column];
    }

    public int getPositiveCells() {
        return positiveCells;
    }

    public CellType getType(int row, int column){
        if(row>=0 && row<rows && column>=0 && column<columns)
            return matrix[row][column].getType();

        return null;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", rows=" + rows +
                ", columns=" + columns +
                '}';
    }
}
