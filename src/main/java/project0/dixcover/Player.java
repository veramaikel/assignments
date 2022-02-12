package project0.dixcover;

import project0.dixcover.structures.DixcoverStack;

public class Player {
    private int bomb;
    private int superbomb;
    private DixcoverStack<Move> moveStack;
    private String name;

    public Player(String name){
        this(name, 0, 0);
    }

    public Player(String name, int bomb, int superbomb) {
        this.bomb = bomb;
        this.superbomb = superbomb;
        this.name = name;
        moveStack = new DixcoverStack<Move>();
    }

    public void addMove(Move move){
        if(move.hasBomb()){
            if(move.getOrientation().equals(OrientationMove.BOTH) && move.getSense().equals(SenseMove.BOTH)){
                removeSuperbomb();
            }
        }
        else {
            removeBomb();
        }
        moveStack.push(move);
    }

    public Move reverseMove(){
        Move move = moveStack.pop();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @java.lang.Override
    public String toString() {

        StringBuffer r = new StringBuffer("Player{name="+this.name);
                r.append(", bomb=" + bomb)
                        .append(", superbomb=" + superbomb)
                        .append(", " + moveStack.toString())
                        .append("}");
        return r.toString();
    }
}
