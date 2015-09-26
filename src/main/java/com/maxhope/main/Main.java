package com.maxhope.main;

import com.maxhope.game.Board;
import com.maxhope.game.Square;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        board.printBoard();
        Square square1 = board.getSquares()[7][1];
        Square square2 = board.getSquares()[2][2];
        board.movePiece(square1, square2);
        board.printBoard();

        square1 = board.getSquares()[1][1];
        square2 = board.getSquares()[2][2];
        board.movePiece(square1, square2);
        board.printBoard();

        Square square3 = board.getSquares()[7][4];
        System.out.println("Square color: "+square3.getColor().toString()+ " "+square3.getPiece().getName());

    }
}
