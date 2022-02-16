package com.dixcover.model;

public class Move extends Position {
    OrientationMove orientation;
    SenseMove sense;
    boolean hasBomb;
    Player player;

    Move(int row, int col, Player player, OrientationMove orientation, SenseMove sense, boolean hasBomb){
        super(row, col);
        this.hasBomb = hasBomb;
        this.orientation = orientation;
        this.sense = sense;
        this.player = player;
    }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public OrientationMove getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationMove orientation) {
        this.orientation = orientation;
    }

    public SenseMove getSense() {
        return sense;
    }

    public void setSense(SenseMove sense) {
        this.sense = sense;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }
}
