package com.dixcover.model;

import com.dixcover.util.DixcoverDeque;
import org.jetbrains.annotations.NotNull;

public class Player {
    private Integer id;
    private int bomb;
    private int superbomb;
    private DixcoverDeque<Move> moves;
    private String name;
    private boolean human;
    private boolean winner;

    public Player(String name){
        this(name, null, 0, 0, true, false);
    }

    public Player(String name, Integer id, int bomb, int superbomb, boolean human, boolean winner) {
        this.bomb = bomb;
        this.superbomb = superbomb;
        this.name = name;
        this.id = id;
        this.human = human;
        this.winner = winner;
        moves = new DixcoverDeque<Move>();
    }

    public void addMove(@NotNull Move move){
        if(move.hasBomb()){
            if(move.getOrientation().equals(OrientationMove.BOTH) && move.getSense().equals(SenseMove.BOTH)){
                removeSuperbomb();
            }
        }
        else {
            removeBomb();
        }
        moves.push(move);
    }

    public Move reverseMove(){
        Move move = moves.pop();
        if(move.hasBomb()){
            if(move.getOrientation().equals(OrientationMove.BOTH) && move.getSense().equals(SenseMove.BOTH)){
                addSuperbomb();
            }
        }
        else {
            addBomb();
        }
        return move;
    }

    public int getBomb() {
        return bomb;
    }

    public void addBomb() {
        this.bomb++;
    }

    public void removeBomb() {
        this.bomb--;
        if(this.bomb<0) this.bomb = 0;
    }

    public int getSuperbomb() {
        return superbomb;
    }

    public void addSuperbomb() {
        this.superbomb++;
    }

    public void removeSuperbomb() {
        this.superbomb--;
        if(this.superbomb<0) this.superbomb = 0;
    }

    public Integer getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHuman() { return human; }

    public void setHuman(boolean human) { this.human = human; }

    @java.lang.Override
    public String toString() {

        StringBuffer r = new StringBuffer("Player{name="+this.name+"("+this.id+")");
                r.append(", bomb=" + bomb)
                        .append(", superbomb=" + superbomb)
                        .append(", human=" + human)
                        .append(", winner=" + human)
                        .append(", " + moves.toString())
                        .append("}");
        return r.toString();
    }

    public String toStr() {
        StringBuffer r = new StringBuffer("Player{name="+this.name+"("+this.id+")");
               r.append(", bomb=" + bomb)
                .append(", superbomb=" + superbomb)
                .append(", human=" + human+"}");
        return r.toString();
    }
}
