package com.maximize.model;

import com.maximize.util.MaximizeLinkedList;
import org.apache.log4j.Logger;

import java.sql.Date;

public class Game {
    private static final Logger log = Logger.getLogger(Game.class);
    public static final int MAX_PLAYERS = 3;
    private Integer id;
    private String name;
    private final Date star;
    private Date update;
    private Board board;
    private MaximizeLinkedList<Player> players;
    private boolean over;

    public Game(String name){
        this(name, null, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),false);
    }

    public Game(String name, Integer id, Date star, Date update, boolean over){
        this.name = name;
        this.id = id;
        this.star = star;
        this.update = update;
        players = new MaximizeLinkedList<>();
        board = null;
        this.over = over;
    }

    public void reversePlay(){
        Player P = this.getPlayer();
        P.reversePlay(this.board);
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

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
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

    public void setBoard(int rows, int columns, int zeroCells) {
        this.board = new Board(rows, columns, zeroCells);
        this.board.setId(this.id);
    }

    public MaximizeLinkedList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    /**
     *
     * @return the player whose turn it is to move
     */
    public Player getPlayer() {
        return this.players.getHead();
    }

    /**
     *
     * @return the next player whose turn it is to move
     */
    public Player nextPlayer() {
        return this.players.next();
    }

    /**
     * Set the player at the head of the list
     * @param p Player
     */
    public void setPlayer(Player p){
        this.players.setHead(p);
    }

    /**
     *
     * @param p Player
     * @return true if the player p is the player in the head
     */
    public boolean isHead(Player p){
        return this.players.isHead(p);
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
                ", over=" + over +
                ", board=" + board +
                ", players=" + players +
                '}';
    }
}
