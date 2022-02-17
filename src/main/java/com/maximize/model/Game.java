package com.maximize.model;

import com.maximize.util.MaximizeLinkedList;
import org.apache.log4j.Logger;

import java.sql.Date;

public class Game {
    private static final Logger log = Logger.getLogger(Game.class);
    private Integer id;
    private String name;
    private Date star;
    private Date update;
    private Board board;
    private MaximizeLinkedList<Player> players;

    public Game(String name){
        this(name, null, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
    }

    public Game(String name, Integer id, Date star, Date update){
        this.name = name;
        this.id = id;
        this.star = star;
        this.update = update;
        players = new MaximizeLinkedList<>();
        board = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStar() {
        return star;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setBoard(int rows, int columns) {
        this.board = new Board(rows, columns);
        this.board.setId(this.id);
    }

    public MaximizeLinkedList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public Player getPlayer() {
        return this.players.getHead();
    }

    public Player nextPlayer() {
        return this.players.next();
    }

    public void setPlayer(Player p){
        this.players.setHead(p);
    }

    public void setHead(Player p){
        int index = this.players.search(p);
        this.players.setHeadIndex(index);
    }

    public void setPlayers(MaximizeLinkedList<Player> list){
        this.players = list;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name(id)=" +name+ "("+ id + ")"+
                ", star=" + star +
                ", update=" + update +
                ", players=" + players +
                '}';
    }
}
