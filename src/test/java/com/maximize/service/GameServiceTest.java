package com.maximize.service;

import com.maximize.dao.GameDao;
import com.maximize.dao.PlayerDao;
import com.maximize.model.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.sql.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameServiceTest {

    GameDao mockGDao;
    PlayerDao mockPDao;
    Game game;
    Connection mockConnection;
    PreparedStatement mockPreparedId;
    PreparedStatement mockPreparedName;
    Statement mockStatement;
    ResultSet mockResultAllGame;
    ResultSet mockResultGetId;
    ResultSet mockResultGetName;
    GameService gService;
    PlayerService pService;
    ConsoleService cService;
    MaxibotService maxiMockService;
    Scanner mockScanner;


    @BeforeAll
    void setUp() throws SQLException {
        Date now = new Date(System.currentTimeMillis());
        mockGDao = Mockito.mock(GameDao.class);
        mockPDao = Mockito.mock(PlayerDao.class);
        mockScanner = Mockito.mock(Scanner.class);
        maxiMockService = Mockito.mock(MaxibotService.class);


        cService = new ConsoleService(mockScanner);
        pService = new PlayerService(mockPDao);
        gService = new GameService(mockGDao, pService, cService, maxiMockService);

        mockConnection = Mockito.mock(Connection.class);
        mockPreparedId = Mockito.mock(PreparedStatement.class);
        mockPreparedName = Mockito.mock(PreparedStatement.class);
        mockStatement = Mockito.mock(Statement.class);
        mockResultAllGame = Mockito.mock(ResultSet.class);
        mockResultGetId = Mockito.mock(ResultSet.class);
        mockResultGetName = Mockito.mock(ResultSet.class);

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

        Mockito.when(mockGDao.get(1)).thenReturn(
                new Game("TestingGame 1", 1, now, now, false));
        Mockito.when(mockGDao.get(2)).thenReturn(new Game("TestingGame 2", 2, now, now, true));
        Mockito.when(mockGDao.get(3)).thenReturn(new Game("TestingGame 3", 3, now, now, false));
        Mockito.when(mockGDao.get(4)).thenReturn(new Game("TestingGame 4", 4, now, now, true));

        Mockito.when(mockGDao.get("TestingGame 1"))
                .thenReturn(new Game("TestingGame 1", 1, now, now, false));
        Mockito.when(mockGDao.get("TestingGame 2"))
                .thenReturn(new Game("TestingGame 2", 2, now, now, true));
        Mockito.when(mockGDao.get("TestingGame 3"))
                .thenReturn(new Game("TestingGame 3", 3, now, now, false));
        Mockito.when(mockGDao.get("TestingGame 4"))
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
    void getAllGames() {
    }

    @Test
    void getAllGamesNames() {
    }

    @Test
    void getGame() {
    }

    @Test
    void newGame() {
    }

    @Test
    void beforeMove() {
    }

    @Test
    void move() {
    }

    @Test
    void afterMove() {
    }
}