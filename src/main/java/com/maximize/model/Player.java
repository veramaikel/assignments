package com.maximize.model;

import com.maximize.util.MaximizeDeque;

public class Player {
    private Integer id;
    private int points;
    private MaximizeDeque<Move> moves;
    private String name;
    private boolean human;

    public Player(String name){
        this(name, null, 0, true);
    }

    public Player(String name, Integer id, int points, boolean human) {
        this.points = points;
        this.name = name;
        this.id = id;
        this.human = human;
        moves = new MaximizeDeque<>();
    }

    public void addMove(Move move){
        moves.push(move);
    }

    public Move reverseMove(){
        //Move move =
        return moves.pop();
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points = this.points + points;
        if(this.points <0) this.points = 0;
    }

    public void duplexPoints() {
        this.points = this.points * 2;
    }

    public void resetPoints() {
        this.points = 0;
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

        return "Player{name=" + this.name + "(" + this.id + ")" + ", points=" + points +
                ", human=" + human +
                ", " + moves.toString() +
                "}";
    }

    public String toStr() {
        return "Player{name=" + this.name + "(" + this.id + ")" + ", points=" + points + ", human=" + human + "}";
    }
}
