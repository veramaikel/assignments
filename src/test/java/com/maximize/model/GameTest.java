package com.maximize.model;

import com.maximize.util.MaximizeLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameTest {

    Game game;
    Board board;
    Player player;

    @BeforeEach
    public void setUp() {
        game = new Game("TestingGame");
        board = new Board(12, 15, 4);
        game.setBoard(board);
        player = new Player("TestingPlayer 1");
        game.addPlayer(player);
    }

    @Test
    void GameReversePlayTest() {
        Move move = new Move(7, 0, player, DirectionMove.RIGHT, true);
        player.addMove(move);
        String before = board.wrapper();
        board.play(move);
        String after = board.wrapper();
        assertNotEquals(0, player.getActualMoves());
        assertNotEquals(before, after);
        game.reversePlay();
        after = board.wrapper();
        assertEquals(before, after);
    }

    @Test
    void GameGetIdTest() {
        assertNull(game.getId());
        game.setId(1);
        assertNotNull(game.getId());
        assertEquals(1, game.getId());
    }

    @Test
    void GameGetNameTest() {
        assertEquals("TestingGame", game.getName());
        game.setName("TestingGame 2");
        assertEquals("TestingGame 2", game.getName());
    }

    @Test
    void GameGetStarTest() {
        Date date = new Date(System.currentTimeMillis());
        assertEquals(date.toString(), game.getStar().toString());
    }

    @Test
    void GameIsOverTest() {
        assertFalse(game.isOver());
        game.setOver(true);
        assertTrue(game.isOver());
    }

    @Test
    void GameGetUpdateTest() {
        Date date = new Date(System.currentTimeMillis()+990000000);
        game.setUpdate(date);
        assertEquals(date.toString(), game.getUpdate().toString());
    }

    @Test
    void GameGetPlayersTest() {
        game.addPlayer(new Player("TestingPlayer 2"));
        game.addPlayer(new Player("TestingPlayer 3"));
        assertEquals(3, game.getPlayers().size());
        for (int i = 1; i <= 3; i++) {
            assertEquals("TestingPlayer "+i,game.getPlayers().getHead().getName());
            game.getPlayers().next();
        }

    }

    @Test
    void GameGetPlayerTest() {
        assertEquals(player, game.getPlayer());
        game.addPlayer(new Player("TestingPlayer 2"));
        assertEquals(player, game.getPlayer());
        game.nextPlayer();
        assertEquals("TestingPlayer 2", game.getPlayer().getName());
    }

    @Test
    void GameNextPlayerTest() {
        game.addPlayer(new Player("TestingPlayer 2"));
        assertEquals(player, game.getPlayer());
        assertEquals("TestingPlayer 2", game.nextPlayer().getName());
    }

    @Test
    void GameIsHeadTest() {
        Player player2 = new Player("TestingPlayer 2");
        game.addPlayer(player2);
        game.addPlayer(new Player("TestingPlayer 3"));
        assertTrue(game.isHead(player));
        game.nextPlayer();
        assertTrue(game.isHead(player2));
    }

    @Test
    void GameSetPlayersTest() {
        MaximizeLinkedList<Player> list = new MaximizeLinkedList<>();
        Player player2 = new Player("TestingPlayer 2");
        Player player3 = new Player("TestingPlayer 3");
        Player player4 = new Player("TestingPlayer 4");
        list.add(player2);
        list.add(player3);
        list.add(player4);
        game.setPlayers(list);
        assertEquals(3, game.getPlayers().size());
        assertEquals(player2, game.getPlayer());
        game.nextPlayer();
        assertEquals(player3, game.getPlayer());
    }
}