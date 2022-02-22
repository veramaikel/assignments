package com.maximize.service;

import com.maximize.model.*;
import org.apache.log4j.Logger;

public class MaxibotService {
    private static final Logger log = Logger.getLogger(MaxibotService.class);

    public MaxibotService(){

    }

    public Move generateMove(Game G){
        Move move = null;
        Board B = G.getBoard();

        int maxRow = -1, row = -1;
        int maxCol = -1, col = -1;
        for (int i = 0; i < B.getRows(); i++) {
            if(B.getHiddenRow(i) > maxRow){
                maxRow = B.getHiddenRow(i);
                row = i;
            }
        }
        for (int i = 0; i < B.getColumns(); i++) {
            if(B.getHiddenColumn(i) > maxCol){
                maxCol = B.getHiddenColumn(i);
                col = i;
            }
        }

        while (move==null) {
            if(maxCol>maxRow){
                for (int i = 0; i < B.getRows(); i++) {
                    if(!B.getType(i, col).equals(CellType.STOP)){
                        move = new Move(i,col,G.getPlayer(),DirectionMove.DOWN, true);
                        break;
                    }
                    else maxCol--;
                }
            }
            else{
                for (int i = 0; i < B.getColumns(); i++) {
                    if(!B.getType(row, i).equals(CellType.STOP)){
                        move = new Move(row,i,G.getPlayer(),DirectionMove.RIGHT, true);
                        break;
                    }
                    else maxRow--;
                }
            }
        }

        return move;
    }
}
