package com.maximize;

import com.maximize.model.Board;

public class Driver {
    public static void main(String[] args) {
        Board board = new Board(8,8);
        board.print(true);
    }
}
