package com.maxhope;

import com.maxhope.game.Square;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRookMoves extends BaseTest {

     @Test
     public void testMoveRookTwoUp() {
        // Move away the above pawn so we can move the king...
        Square square1 = board.getSquares()[1][0];
        Square square2 = board.getSquares()[3][0];
        board.movePiece(square1, square2);
        board.printBoard();

        // Move rook...
        square1 = board.getSquares()[0][0];
        square2 = board.getSquares()[2][0];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved);
        board.printBoard();
    }

    @Test
    public void testMoveRookOverOwnPawn() {
        // Move away the above pawn so we can move the king...
        Square square1 = board.getSquares()[1][0];
        Square square2 = board.getSquares()[3][0];
        board.movePiece(square1, square2);
        board.printBoard();

        // Move rook...
        square1 = board.getSquares()[0][0];
        square2 = board.getSquares()[4][0];
        boolean moved = board.movePiece(square1, square2);
        board.printBoard();
        Assert.assertFalse("Expected false but the move succeeded!", moved);
    }

    @Test
    public void testMoveRookUpAndDown() {
        Square[][] squares = super.createPieces(false,false,false,false,true,false);
        board.placePieces(squares);

        // Move rook up...
        Square square1 = board.getSquares()[0][0];
        Square square2 = board.getSquares()[4][0];
        boolean moved = board.movePiece(square1, square2);
        board.printBoard();
        Assert.assertTrue("Expected true but the move failed!", moved);

        // Move rook back...
        square1 = board.getSquares()[4][0];
        square2 = board.getSquares()[0][0];
        boolean movedBack = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", movedBack);
        board.printBoard();
    }

    @Test
    public void testMoveRookToTopAndKillEnemyRook() {
        Square[][] squares = super.createPieces(false,false,false,false,true,false);
        board.placePieces(squares);
        // Move rook...
        Square square1 = board.getSquare(0, 0);
        Square square2 = board.getSquare(7, 0);
        boolean moved = board.movePiece(square1, square2);
        board.printBoard();
        Assert.assertTrue("Expected true but the move failed!", moved);
    }

    @Test
    public void testMoveRook6StepsRight() {
        Square[][] squares = super.createPieces(false,false,false,false,true,false);
        board.placePieces(squares);
        // Move rook...
        Square square1 = board.getSquare(0, 0);
        Square square2 = board.getSquare(0, 6);
        boolean moved = board.movePiece(square1, square2);
        board.printBoard();
        Assert.assertTrue("Expected true but the move failed!", moved);
    }

    @Test
    public void testMoveRook7StepsRight() {
        Square[][] squares = super.createPieces(false,false,false,false,true,false);
        board.placePieces(squares);
        // Move rook...
        Square square1 = board.getSquares()[0][0];
        Square square2 = board.getSquares()[0][7];
        boolean moved = board.movePiece(square1, square2);
        board.printBoard();
        Assert.assertFalse("Expected false but the move succeeded!", moved);
    }
}
