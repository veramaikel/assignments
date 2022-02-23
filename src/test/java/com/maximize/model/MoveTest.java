package com.maximize.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MoveTest {

    Move move1;
    Move move2;
    Move move3;
    Move move4;
    Player player1;
    Player player2;

    @BeforeAll
    public void setUp() {
        player1 = new Player("TestingMove1");
        player2 = new Player("TestingMove2");
        move1 = new Move(3, 5, player1, DirectionMove.UP, false);
        move2 = new Move(7, 9, player1, DirectionMove.DOWN, true);
        move3 = new Move(0, 14, player2, DirectionMove.LEFT, false);
        move4 = new Move(13, 2, player2, DirectionMove.RIGHT, true);
    }

    @Test
    void MoveGetRowTest() {
        assertEquals(3, move1.getRow());
        assertEquals(7, move2.getRow());
        assertEquals(0, move3.getRow());
        assertEquals(13, move4.getRow());
    }

    @Test
    void MoveSetRowTest() {
        assertEquals(3, move1.getRow());
        move1.setRow(5);
        assertEquals(5, move1.getRow());
        assertEquals(7, move2.getRow());
        move2.setRow(9);
        assertEquals(9, move2.getRow());
    }

    @Test
    void MoveGetColumnTest() {
        assertEquals(5, move1.getColumn());
        assertEquals(9, move2.getColumn());
        assertEquals(14, move3.getColumn());
        assertEquals(2, move4.getColumn());
    }

    @Test
    void MoveSetColumnTest() {
        assertEquals(5, move1.getColumn());
        move1.setColumn(3);
        assertEquals(3, move1.getColumn());
        assertEquals(9, move2.getColumn());
        move2.setColumn(7);
        assertEquals(7, move2.getColumn());
    }

    @Test
    void MoveGetPlayerTest() {
        assertEquals(player2, move3.getPlayer());
        assertEquals(player1, move1.getPlayer());
        assertEquals(player2, move4.getPlayer());
        assertEquals(player1, move2.getPlayer());

    }

    @Test
    void MoveSetPlayerTest() {
        assertEquals(player2, move3.getPlayer());
        move3.setPlayer(player1);
        assertEquals(player1, move3.getPlayer());
        assertEquals(player1, move1.getPlayer());
        move1.setPlayer(player2);
        assertEquals(player2, move1.getPlayer());
    }

    @Test
    void MoveGetDirectionTest() {
        assertEquals(DirectionMove.UP, move1.getDirection());
        assertEquals(DirectionMove.DOWN, move2.getDirection());
        assertEquals(DirectionMove.LEFT, move3.getDirection());
        assertEquals(DirectionMove.RIGHT, move4.getDirection());
    }

    @Test
    void MoveSetDirectionTest() {
        assertEquals(DirectionMove.UP, move1.getDirection());
        move1.setDirection(DirectionMove.LEFT);
        assertEquals(DirectionMove.LEFT, move1.getDirection());
        assertEquals(DirectionMove.LEFT, move3.getDirection());
        move3.setDirection(DirectionMove.DOWN);
        assertEquals(DirectionMove.DOWN, move3.getDirection());
    }

    @Test
    void MoveTurn90Test() {
        assertEquals(DirectionMove.UP, move1.getDirection());
        assertEquals(3, move1.getRow());
        assertEquals(5, move1.getColumn());
        Move move = move1.turn90();
        assertEquals(DirectionMove.RIGHT, move.getDirection());
        assertEquals(3, move.getRow());
        assertEquals(6, move.getColumn());
    }

    @Test
    void MoveTurn270Test() {
        assertEquals(DirectionMove.RIGHT, move4.getDirection());
        assertEquals(13, move4.getRow());
        assertEquals(2, move4.getColumn());
        Move move = move4.turn270();
        assertEquals(DirectionMove.UP, move.getDirection());
        assertEquals(12, move.getRow());
        assertEquals(2, move.getColumn());
    }

    @Test
    void MoveAheadTest() {
        move2.setRow(11);
        move2.setColumn(14);
        assertEquals(DirectionMove.DOWN, move2.getDirection());
        assertEquals(11, move2.getRow());
        assertEquals(14, move2.getColumn());
        Move move = move2.ahead();
        assertEquals(DirectionMove.DOWN, move.getDirection());
        assertEquals(12, move.getRow());
        assertEquals(14, move.getColumn());
    }

    @Test
    void MoveAddPointsTest() {
        assertEquals(0, move1.getPoints());
        move1.addPoints(50);
        assertEquals(50, move1.getPoints());
        move1.addPoints(-74);
        assertEquals(-24, move1.getPoints());
    }

    @Test
    void MoveIsFirstTest() {
        assertFalse(move1.isFirst());
        assertTrue(move2.isFirst());
        assertFalse(move3.isFirst());
        assertTrue(move4.isFirst());
    }
}