package com.maximize.dao;

import com.maximize.model.Board;
import com.maximize.model.Game;
import com.maximize.model.Player;
import com.maximize.util.ConnectionUtil;
import com.maximize.util.MaximizeLinkedList;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    private static final Logger log = Logger.getLogger(GameDao.class);
    private final Connection conn;

    public GameDao(){
        conn = ConnectionUtil.getConnection();
    }

    public List<Game> getAllGames() throws SQLException {
        List<Game> allGames = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("Select * From Game");
        while(rs.next()){
            Game nextGame = new Game(rs.getString("name"),rs.getInt("id"),
                    rs.getDate("start_date"), rs.getDate("update_date"),
                    rs.getBoolean("gameover"));
            allGames.add(nextGame);
        }
        rs.close();
        return allGames;
    }

    public Game get(Integer id) throws SQLException {
        Game myGame = null;
        PreparedStatement statement = conn.prepareStatement("Select * From Game Where id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            myGame = new Game(rs.getString("name"),rs.getInt("id"),
                    rs.getDate("start_date"), rs.getDate("update_date"),
                    rs.getBoolean("gameover"));
        }
        if(myGame!=null) {
            myGame.setBoard(this.getBoard(myGame.getId()));
            myGame.setPlayers(this.getPlayersByGame(myGame.getId()));
        }
        rs.close();
        return myGame;
    }

    public Game get(String name) throws SQLException {
        Game myGame = null;
        PreparedStatement statement = conn.prepareStatement("Select * From Game Where name = ?");
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            myGame = new Game(rs.getString("name"),rs.getInt("id"),
                    rs.getDate("start_date"), rs.getDate("update_date"),
                    rs.getBoolean("gameover"));
        }
        if(myGame!=null) {
            myGame.setBoard(this.getBoard(myGame.getId()));
            myGame.setPlayers(this.getPlayersByGame(myGame.getId()));
        }
        rs.close();
        return myGame;
    }

    public Game add(Game G) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "Insert into Game (name, start_date, update_date, gameover) " +
                        "Values" +
                        "(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        int parameterIndex = 0;
        statement.setString(++parameterIndex, G.getName());
        statement.setDate(++parameterIndex, G.getStar());
        statement.setDate(++parameterIndex, G.getUpdate());
        statement.setBoolean(++parameterIndex, G.isOver());
        conn.setAutoCommit(false);
            if(statement.executeUpdate()>0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    G.setId(rs.getInt(1));
                    G.getBoard().setId(G.getId());
                    log.debug("Game Inserted ID = " + G.getId());
                }
                this.addBoard(G.getBoard());
                MaximizeLinkedList<Player> list = G.getPlayers();
                if(!list.empty()){
                    Player head = list.getHead();
                    while (!list.next().equals(head)) {
                        this.addPlaying(G.getId(),list.getHead(),false);
                    }
                    this.addPlaying(G.getId(),head,true);
                }
                conn.commit();
                rs.close();
            }
        conn.setAutoCommit(true);
        return G;
    }

    public void update(Game G) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE Game SET update_date = ?, gameover = ? Where id = ?");
        int parameterIndex = 0;
        statement.setDate(++parameterIndex, G.getUpdate());
        statement.setBoolean(++parameterIndex, G.isOver());
        statement.setInt(++parameterIndex, G.getId());
        conn.setAutoCommit(false);
        if(statement.executeUpdate()>0) {
            log.debug("Game Updated ID = " + G.getId());
            this.updateBoard(G.getBoard());
            MaximizeLinkedList<Player> list = G.getPlayers();
            if(!list.empty()){
                Player head = list.getHead();
                while (!list.next().equals(head)) {
                    this.updatePlaying(G.getId(),list.getHead(),false);
                }
                this.updatePlaying(G.getId(),head,true);
            }
            conn.commit();
        }
        conn.setAutoCommit(true);
    }

    private Board getBoard(Integer id) throws SQLException {
        Board myBoard = null;
        PreparedStatement statement = conn.prepareStatement("Select * From Board Where id_game = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            myBoard = new Board(rs.getInt("id_game"), rs.getString("matrix"));
        }

        rs.close();
        return myBoard;
    }

    private void addBoard(Board B) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "Insert into Board (id_game, matrix) Values (?,?)");
        int parameterIndex = 0;
        statement.setInt(++parameterIndex, B.getId());
        statement.setString(++parameterIndex, B.wrapper());
        statement.executeUpdate();
    }

    private void updateBoard(Board B) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE Board SET matrix = ? Where id_game = ?");
        int parameterIndex = 0;
        statement.setString(++parameterIndex, B.wrapper());
        statement.setInt(++parameterIndex, B.getId());
        statement.executeUpdate();
    }

    private MaximizeLinkedList<Player> getPlayersByGame(Integer id) throws SQLException {
        MaximizeLinkedList<Player> myPlayers = new MaximizeLinkedList<>();
        String sql = "Select P.*, G.* "
                + "From Player P Inner Join Playing G on P.id = G.id_player "
                + "Where G.id_game = ? order by G.turn DESC";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        int head = -1;
        int rsIndex = -1;
        while(rs.next()){
            rsIndex++;
            Player P =new Player(rs.getString("name"),
                    rs.getInt("id_player"), rs.getBoolean("human"));
            P.addPoints(rs.getInt("points"));
            if(rs.getBoolean("turn") && head<0) head = rsIndex;
            myPlayers.add(P);
        }
        myPlayers.setHeadIndex(head);
        rs.close();
        return myPlayers;
    }

    private void addPlaying(Integer id, Player P, boolean turn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "Insert into Playing (id_game, id_player, turn, points)" +
                        "Values" +
                        "(?,?,?,?)");
        int parameterIndex = 0;
        statement.setInt(++parameterIndex, id);
        statement.setInt(++parameterIndex, P.getId());
        statement.setBoolean(++parameterIndex, turn);
        statement.setInt(++parameterIndex, P.getPoints());
        statement.executeUpdate();
    }

    private void updatePlaying(Integer id, Player P, boolean turn) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "Update Playing SET turn = ?, points = ? Where id_game = ? AND id_player = ?");
        int parameterIndex = 0;
        statement.setBoolean(++parameterIndex, turn);
        statement.setInt(++parameterIndex, P.getPoints());
        statement.setInt(++parameterIndex, id);
        statement.setInt(++parameterIndex, P.getId());
        statement.executeUpdate();
    }
}
