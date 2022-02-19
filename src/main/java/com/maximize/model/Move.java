package com.maximize.model;

public class Move extends Position {
    DirectionMove direction;
    Player player;

    public Move(int row, int col, Player player, DirectionMove direction){
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

    public Move turn90(){
        if(direction.equals(DirectionMove.UP))
            return new Move(getRow(),getColumn()+1,player,DirectionMove.RIGHT);
        else if(direction.equals(DirectionMove.DOWN))
            return new Move(getRow(),getColumn()-1,player,DirectionMove.LEFT);
        else if(direction.equals(DirectionMove.LEFT))
            return new Move(getRow()-1,getColumn(),player,DirectionMove.UP);
        else //Right
            return new Move(getRow()+1,getColumn(),player,DirectionMove.DOWN);
    }

    public Move turn270(){
        if(direction.equals(DirectionMove.UP))
            return new Move(getRow(),getColumn()-1,player,DirectionMove.LEFT);
        else if(direction.equals(DirectionMove.DOWN))
            return new Move(getRow(),getColumn()+1,player,DirectionMove.RIGHT);
        else if(direction.equals(DirectionMove.LEFT))
            return new Move(getRow()+1,getColumn(),player,DirectionMove.DOWN);
        else //Right
            return new Move(getRow()-1,getColumn(),player,DirectionMove.UP);
    }

    public Move ahead(){
        if(direction.equals(DirectionMove.UP))
            return new Move(getRow()-1,getColumn(),player,DirectionMove.UP);
        else if(direction.equals(DirectionMove.DOWN))
            return new Move(getRow()+1,getColumn(),player,DirectionMove.DOWN);
        else if(direction.equals(DirectionMove.LEFT))
            return new Move(getRow(),getColumn()-1,player,DirectionMove.LEFT);
        else //Right
            return new Move(getRow(),getColumn()+1,player,DirectionMove.RIGHT);
    }

    @java.lang.Override
    public String toString() {
        return "Move{row=" + getRow() + ", col=" + getColumn() + ", direction=" + direction + "}";
    }
}
