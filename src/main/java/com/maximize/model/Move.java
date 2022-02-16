package com.maximize.model;

public class Move extends Position {
    OrientationMove orientation;
    SenseMove sense;
    Player player;

    Move(int row, int col, Player player, OrientationMove orientation, SenseMove sense){
        super(row, col);
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
}
