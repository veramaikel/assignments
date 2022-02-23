package com.maximize.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BoardTest {

    Board board1;
    Board board2;
    Board board3;
    Player player;
    StringBuilder board4x6;

    @BeforeAll
    public void setUp() {
        board1 = new Board(8, 12, 4);
        board2 = new Board(15, 9, 3);
        board4x6 = new StringBuilder()
                .append(CellWrapper.HIDDEN.getValue()).append(CellType.EMPTY.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.STOP.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.POINT.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.DUPLEX.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.EMPTY.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.POINT.getValue())
                .append(CellWrapper.SPLIT.getValue())
                .append(CellWrapper.HIDDEN.getValue()).append(CellType.ZERO.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.POINT.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.DUPLEX.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.STOP.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.EMPTY.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.PLUS.getValue())
                .append(CellWrapper.SPLIT.getValue())
                .append(CellWrapper.HIDDEN.getValue()).append(CellType.POINT.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.DUPLEX.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.EMPTY.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.POINT.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.STOP.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.EMPTY.getValue())
                .append(CellWrapper.SPLIT.getValue())
                .append(CellWrapper.HIDDEN.getValue()).append(CellType.PLUS.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.POINT.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.STOP.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.EMPTY.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.HIDDEN.getValue()).append(CellType.DUPLEX.getValue())
                .append(CellWrapper.COLUMN.getValue()).append(CellWrapper.SHOW.getValue()).append(CellType.EMPTY.getValue());


        player = new Player("Testing");

    }

    @BeforeEach
    public void beforeTest(){
        board3 = new Board(1, board4x6.toString());
    }

    @Test
    public void BoardWrapperTest() {
        String wrapperString = board3.wrapper();
        assertEquals(wrapperString, board4x6.toString());
    }

    @Test
    public void BoardUnwrapperTest() {
        Board board = new Board(4, board4x6.toString());
        assertEquals(board.getColumns(), 6);
        assertEquals(board.getRows(), 4);
        assertEquals(board.getZeroCells(), 1);
        assertEquals(board.getPositiveCells(), 6);
        assertEquals(board.getHiddenTotal(), 14);
        assertEquals(board.getHiddenRow(3), 3);
        assertEquals(board.getHiddenColumn(1), 4);
    }

    @Test
    void BoardPlayTest() {
        player.addPoints(10);
        Move move = new Move(3,0,player,DirectionMove.UP,true);
        board3.play(move);
        assertEquals(0, player.getPoints());
        assertEquals(board3.getHiddenTotal(), 9);
        assertEquals(board3.getPositiveCells(), 4);
        assertEquals(board3.getZeroCells(), 0);
        assertEquals(board3.getHiddenRow(3), 1);
        assertEquals(board3.getHiddenColumn(1), 3);
        assertEquals(player.getActualMoves(), 5);
    }

    @Test
    void BoardIsInsideTest() {
        assertTrue(board1.isInside(new Move(5, 10, player, DirectionMove.UP, false)));
        assertTrue(board1.isInside(new Move(0, 0, player, DirectionMove.UP, false)));
        assertFalse(board1.isInside(new Move(8, 10, player, DirectionMove.UP, false)));
        assertFalse(board1.isInside(new Move(5, 12, player, DirectionMove.UP, false)));
    }

    @Test
    void BoardSetHiddenCellTest() {
        assertEquals(board3.getZeroCells(), 1);
        assertEquals(board3.getHiddenTotal(), 14);
        assertEquals(board3.getPositiveCells(), 6);
        assertEquals(board3.getHiddenRow(1), 4);
        assertEquals(board3.getHiddenColumn(0), 4);
        assertEquals(board3.getHiddenRow(2), 3);
        assertEquals(board3.getHiddenColumn(3), 1);
        board3.setHiddenCell(1,0, false);
        board3.setHiddenCell(3,1, false);
        board3.setHiddenCell(2,3, false);
        assertEquals(board3.getZeroCells(), 0);
        assertEquals(board3.getHiddenTotal(), 12);
        assertEquals(board3.getPositiveCells(), 5);
        assertEquals(board3.getHiddenRow(1), 3);
        assertEquals(board3.getHiddenColumn(0), 3);
        assertEquals(board3.getHiddenRow(2), 3);
        assertEquals(board3.getHiddenColumn(3), 1);
        assertEquals(board3.getHiddenRow(0), 4);
        assertEquals(board3.getHiddenColumn(5), 0);
        board3.setHiddenCell(0,5, true);
        assertEquals(board3.getHiddenTotal(), 13);
        assertEquals(board3.getPositiveCells(), 6);
        assertEquals(board3.getZeroCells(), 0);
        assertEquals(board3.getHiddenRow(0), 5);
        assertEquals(board3.getHiddenColumn(5), 1);
    }

    @Test
    void BoardGetRowsTest() {
        Board board = new Board(20,8, 5);
        assertEquals(board1.getRows(), 8);
        assertEquals(board.getRows(), 20);
        assertEquals(board2.getRows(), 15);
    }

    @Test
    void BoardGetColumnsTest() {
        Board board = new Board(20,8, 5);
        assertEquals(board1.getColumns(), 12);
        assertEquals(board2.getColumns(), 9);
        assertEquals(board.getColumns(), 8);
    }

    @Test
    void BoardGetHiddenRowTest() {
        assertEquals(board3.getHiddenRow(0), 4);
        assertEquals(board3.getHiddenRow(1), 4);
        assertEquals(board3.getHiddenRow(2), 3);
        assertEquals(board3.getHiddenRow(3), 3);
        board3.setHiddenCell(0,0,false);
        board3.setHiddenCell(3,5,false);
        assertEquals(board3.getHiddenRow(0), 3);
        assertEquals(board3.getHiddenRow(3), 3);
    }

    @Test
    void BoardGetHiddenTotalTest() {
        assertEquals(board3.getHiddenTotal(), 14);
        board3.setHiddenCell(0,0,false);
        assertEquals(board3.getHiddenTotal(), 13);
        board3.setHiddenCell(3,5,false);
        assertEquals(board3.getHiddenTotal(), 13);
        board3.setHiddenCell(3,5,true);
        assertEquals(board3.getHiddenTotal(), 14);
    }

    @Test
    void BoardGetHiddenColumnTest() {
        assertEquals(board3.getHiddenColumn(0), 4);
        assertEquals(board3.getHiddenColumn(1), 4);
        assertEquals(board3.getHiddenColumn(2), 1);
        assertEquals(board3.getHiddenColumn(3), 1);
        assertEquals(board3.getHiddenColumn(4), 4);
        assertEquals(board3.getHiddenColumn(5), 0);
        board3.setHiddenCell(0,0,false);
        board3.setHiddenCell(3,5,false);
        assertEquals(board3.getHiddenColumn(0), 3);
        assertEquals(board3.getHiddenColumn(5), 0);
        board3.setHiddenCell(3,5,true);
        assertEquals(board3.getHiddenColumn(5), 1);
    }

    @Test
    void BoardGetPositiveCellsTest() {
        assertEquals(board3.getPositiveCells(), 6);
        board3.setHiddenCell(3,5,true);
        board3.setHiddenCell(2,3,true);
        board3.setHiddenCell(0,3,true);
        board3.setHiddenCell(3,1,true);
        assertEquals(board3.getPositiveCells(), 8);
    }

    @Test
    void BoardGetZeroCellsTest() {
        assertEquals(board3.getZeroCells(), 1);
        board3.setHiddenCell(3,5,true);
        assertEquals(board3.getZeroCells(), 1);
        board3.setHiddenCell(1,0,true);
        assertEquals(board3.getZeroCells(), 1);
        board3.setHiddenCell(1,0,false);
        assertEquals(board3.getZeroCells(), 0);
    }

    @Test
    void BoardGetTypeTest() {
        assertEquals(CellType.ZERO,board3.getType(1,0));
        assertEquals(CellType.STOP,board3.getType(0,1));
        assertEquals(CellType.DUPLEX,board3.getType(2,1));
        assertEquals(CellType.EMPTY,board3.getType(3,5));
        assertEquals(CellType.POINT,board3.getType(0,2));
        assertEquals(CellType.PLUS,board3.getType(3,0));
    }
}