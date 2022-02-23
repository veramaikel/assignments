package com.maximize.dao;

import com.maximize.model.Board;
import com.maximize.model.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameDaoTest {

    GameDao gDao;
    GameDao mockDao;
    Game game;
    Connection mockConnection;
    PreparedStatement mockPreparedId;
    PreparedStatement mockPreparedName;
    Statement mockStatement;
    ResultSet mockResultAllGame;
    ResultSet mockResultGetId;
    ResultSet mockResultGetName;


    @BeforeAll
    void setUp() throws SQLException {
        Date now = new Date(System.currentTimeMillis());
        mockDao = Mockito.mock(GameDao.class);

        mockConnection = Mockito.mock(Connection.class);
        mockPreparedId = Mockito.mock(PreparedStatement.class);
        mockPreparedName = Mockito.mock(PreparedStatement.class);
        mockStatement = Mockito.mock(Statement.class);
        mockResultAllGame = Mockito.mock(ResultSet.class);
        mockResultGetId = Mockito.mock(ResultSet.class);
        mockResultGetName = Mockito.mock(ResultSet.class);

        gDao = new GameDao(mockConnection);

        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery("Select * From Game")).thenReturn(mockResultAllGame);
        Mockito.when(mockResultAllGame.next())
                .thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

        Mockito.when(mockResultAllGame.getInt("id")).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);
        Mockito.when(mockResultAllGame.getString("name"))
                .thenReturn("TestingGame 1").thenReturn("TestingGame 2")
                .thenReturn("TestingGame 3").thenReturn("TestingGame 4");
        Mockito.when(mockResultAllGame.getDate("start_date"))
                .thenReturn(now).thenReturn(now).thenReturn(now).thenReturn(now);
        Mockito.when(mockResultAllGame.getDate("update_date"))
                .thenReturn(now).thenReturn(now).thenReturn(now).thenReturn(now);
        Mockito.when(mockResultAllGame.getBoolean("gameover"))
                .thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(true);

        Mockito.when(mockDao.get(1)).thenReturn(
                new Game("TestingGame 1", 1, now, now, false));
        Mockito.when(mockDao.get(2)).thenReturn(new Game("TestingGame 2", 2, now, now, true));
        Mockito.when(mockDao.get(3)).thenReturn(new Game("TestingGame 3", 3, now, now, false));
        Mockito.when(mockDao.get(4)).thenReturn(new Game("TestingGame 4", 4, now, now, true));

        Mockito.when(mockDao.get("TestingGame 1"))
                .thenReturn(new Game("TestingGame 1", 1, now, now, false));
        Mockito.when(mockDao.get("TestingGame 2"))
                .thenReturn(new Game("TestingGame 2", 2, now, now, true));
        Mockito.when(mockDao.get("TestingGame 3"))
                .thenReturn(new Game("TestingGame 3", 3, now, now, false));
        Mockito.when(mockDao.get("TestingGame 4"))
                .thenReturn(new Game("TestingGame 4", 4, now, now, true));

        Mockito.when(mockConnection.prepareStatement("Select * From Game Where id = ?"))
                .thenReturn(mockPreparedId);
        Mockito.when(mockPreparedId.executeQuery()).thenReturn(mockResultGetId);
        Mockito.when(mockResultGetId.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultGetId.getString("name"))
                .thenReturn("TestingGame 1").thenReturn("TestingGame 2")
                .thenReturn("TestingGame 3").thenReturn("TestingGame 4");
        Mockito.when(mockResultGetId.getDate("start_date"))
                .thenReturn(now).thenReturn(now).thenReturn(now).thenReturn(now);
        Mockito.when(mockResultGetId.getDate("update_date"))
                .thenReturn(now).thenReturn(now).thenReturn(now).thenReturn(now);
        Mockito.when(mockResultGetId.getBoolean("gameover"))
                .thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(true);

        Mockito.when(mockConnection.prepareStatement("Select * From Game Where name = ?"))
                .thenReturn(mockPreparedName);
        Mockito.when(mockPreparedName.executeQuery()).thenReturn(mockResultGetName);
        Mockito.when(mockResultGetName.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    void GameDaoGetAllGamesTest() throws SQLException {
        Date now = new Date(System.currentTimeMillis());
        List<Game> gameList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            gameList.add(new Game("TestingGame "+i, i, now, now, ((i%2)==0)));
        }
        List<Game> list = gDao.getAllGames();
        assertEquals(4, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(gameList.get(i).toString(), list.get(i).toString());
        }
    }

    @Test
    void GameDaoGetIdTest() throws SQLException {
        assertEquals(1, mockDao.get(1).getId());
        assertEquals(2, mockDao.get(2).getId());
        assertEquals(3, mockDao.get(3).getId());
        assertEquals(4, mockDao.get(4).getId());
    }

    @Test
    void GameDaoGetNameTest() throws SQLException {
        assertEquals("TestingGame 1", mockDao.get("TestingGame 1").getName());
        assertEquals("TestingGame 2", mockDao.get("TestingGame 2").getName());
        assertEquals("TestingGame 3", mockDao.get("TestingGame 3").getName());
        assertEquals("TestingGame 4", mockDao.get("TestingGame 4").getName());
    }

    @Test
    void GameDaoAddTest() throws SQLException {
        mockDao.add(new Game("TestingGame 1",1,null, null, false));
        assertEquals(1, mockDao.get(1).getId());
        assertEquals("TestingGame 1", mockDao.get(1).getName());
    }

    @Test
    void GameDaoUpdateTest() throws SQLException {
        Date now = new Date(System.currentTimeMillis());
        mockDao.update(
                new Game("TestingGame 1",1,null, now, false));
        assertEquals(now.toString(), mockDao.get(1).getUpdate().toString());
    }
}