package com.maximize.dao;

import com.maximize.model.Player;
import com.maximize.util.ConnectionUtil;
import com.maximize.util.MaximizeLinkedList;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class playerDAO {
    private static final Logger log = Logger.getLogger(playerDAO.class);
    private static Connection conn = ConnectionUtil.getConnection();

    public List<Player> getAllPlayers() throws SQLException {
        List<Player> allPlayers = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("Select * From Player");
        while(rs.next()){
            Player nextPlayer = new Player(rs.getString("name"),
                    rs.getInt("id"), rs.getBoolean("human"));
            allPlayers.add(nextPlayer);
        }
        rs.close();
        return allPlayers;
    }

    public Player get(Integer id) throws SQLException {
        Player myPlayer = null;
        PreparedStatement statement = conn.prepareStatement("Select * From Player Where id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            myPlayer = new Player(rs.getString("name"),
                    rs.getInt("id"), rs.getBoolean("human"));
        }
        rs.close();
        return myPlayer;
    }

    public Player get(String name) throws SQLException {
        Player myPlayer = null;
        PreparedStatement statement = conn.prepareStatement("Select * From Game Where name = ?");
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            myPlayer = new Player(rs.getString("name"),
                    rs.getInt("id"), rs.getBoolean("human"));
        }
        rs.close();
        return myPlayer;
    }

    public Player add(Player P) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                "Insert into Player (name, human)" +
                        "Values" +
                        "(?,?)",Statement.RETURN_GENERATED_KEYS);
        int parameterIndex = 0;
        statement.setString(++parameterIndex, P.getName());
        statement.setBoolean(++parameterIndex, P.isHuman());
        if(statement.executeUpdate()>0) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                P.setId(rs.getInt(1));
                log.debug("Player Inserted ID = " + P.getId());
            }
            rs.close();
        }
        return P;
    }

}
