package com.maximize.model;

public class Move extends Position {
    private DirectionMove direction;
    private Player player;
    private int points;
    private boolean first;

    public Move(int row, int col, Player player, DirectionMove direction, boolean first){
        super(row, col);
        this.direction = direction;
        this.player = player;
        this.points = 0;
        this.first = first;
    }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public DirectionMove getDirection() {
        return direction;
    }

    public void setDirection(DirectionMove direction) {
        this.direction = direction;
    }

    public Move turn90(){
        if(direction.equals(DirectionMove.UP))
            return new Move(getRow(),getColumn()+1,player,DirectionMove.RIGHT, false);
        else if(direction.equals(DirectionMove.DOWN))
            return new Move(getRow(),getColumn()-1,player,DirectionMove.LEFT, false);
        else if(direction.equals(DirectionMove.LEFT))
            return new Move(getRow()-1,getColumn(),player,DirectionMove.UP, false);
        else //Right
            return new Move(getRow()+1,getColumn(),player,DirectionMove.DOWN, false);
    }

    public Move turn270(){
        if(direction.equals(DirectionMove.UP))
            return new Move(getRow(),getColumn()-1,player,DirectionMove.LEFT, false);
        else if(direction.equals(DirectionMove.DOWN))
            return new Move(getRow(),getColumn()+1,player,DirectionMove.RIGHT, false);
        else if(direction.equals(DirectionMove.LEFT))
            return new Move(getRow()+1,getColumn(),player,DirectionMove.DOWN, false);
        else //Right
            return new Move(getRow()-1,getColumn(),player,DirectionMove.UP, false);
    }

    public Move ahead(){
        if(direction.equals(DirectionMove.UP))
            return new Move(getRow()-1,getColumn(),player,DirectionMove.UP, false);
        else if(direction.equals(DirectionMove.DOWN))
            return new Move(getRow()+1,getColumn(),player,DirectionMove.DOWN, false);
        else if(direction.equals(DirectionMove.LEFT))
            return new Move(getRow(),getColumn()-1,player,DirectionMove.LEFT, false);
        else //Right
            return new Move(getRow(),getColumn()+1,player,DirectionMove.RIGHT, false);
    }

    public void addPoints(int points){
        this.points = this.points + points;
    }

    public boolean isFirst() {
        return first;
    }

    public int getPoints(){
        return this.points;
    }

    @Override
    public String toString() {
        return "Move{row=" + getRow() +
                ", column=" + getColumn() +
                ", direction=" + direction +
                ", points=" + points +
                ", first=" + first +
                ", player=" + player +
                '}';
    }
}
