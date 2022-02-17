package com.maximize.model;

public class Move extends Position {
    DirectionMove direction;
    Player player;

    Move(int row, int col, Player player, DirectionMove direction){
        super(row, col);
        this.direction = direction;
        this.player = player;
    }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public DirectionMove getDirection() {
        return direction;
    }

    public void setDirection(DirectionMove direction) {
        this.direction = direction;
    }

    @java.lang.Override
    public String toString() {
        return "Move{row=" + getRow() + ", col=" + getColumn() + ", direction=" + direction.name() + "}";
    }
}
