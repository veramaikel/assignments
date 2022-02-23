package com.maximize.service;

import com.maximize.dao.PlayerDao;
import com.maximize.model.Player;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class PlayerService {
    private static final Logger log = Logger.getLogger(GameService.class);
    private PlayerDao dao;

    public PlayerService(){
        this(new PlayerDao());
    }
    public PlayerService(PlayerDao dao){
        this.dao = dao;
    }

    public List<Player> getAllPlayers() {
        try {
            return dao.getAllPlayers();
        } catch (SQLException e) {
            log.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    public Player getPlayer(String name) {
        Player P;
        try {
            P = dao.get(name);
        } catch (SQLException e) {
            log.error(e.getMessage(), e.getCause());
            return null;
        }
        return P;
    }

    public Player addPlayer(Player P) {
        try {
            if(P.getId()==null) return dao.add(P);
        } catch (SQLException e) {
            log.error(e.getMessage(), e.getCause());
            return null;
        }
        return P;
    }

    public boolean setPlayer(Player P) {
        try {
            return dao.update(P);
        } catch (SQLException e) {
            log.error(e.getMessage(), e.getCause());
            return false;
        }
    }
}
