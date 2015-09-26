package com.maxhope;

import com.maxhope.game.Square;
import org.junit.Assert;
import org.junit.Test;

public class TestBishopMoves extends BaseTest {

    @Test
    public void testMove5LeftDiagonallyUp() {
        Square[][] squares = super.createPieces(true,true,true,true,true,false);
        board.placePieces(squares);

        // Move the bishop diagonally 2 up left
        Square square1 = board.getSquares()[0][2];
        Square square2 = board.getSquares()[5][7];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();
    }

    @Test
     public void testMove5LeftDiagonallyUpAndKillEnemy() {
        Square[][] squares = super.createPieces(true,true,true,true,true,true);
        board.placePieces(squares);

        // Move the bishop diagonally 2 up left
        Square square1 = board.getSquares()[1][3];
        Square square2 = board.getSquares()[3][3];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        // Move the bishop diagonally 2 up left
        square1 = board.getSquares()[6][7];
        square2 = board.getSquares()[5][7];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved2);
        board.printBoard();

        // Move the bishop diagonally 2 up left
        square1 = board.getSquares()[0][2];
        square2 = board.getSquares()[5][7];
        boolean moved3 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved3);
        board.printBoard();
    }

    @Test
    public void testMove5LeftDiagonallyUpAndFail() {
        Square[][] squares = super.createPieces(true, true, true, true, true, true);
        board.placePieces(squares);

        // Move the bishop diagonally 2 up left
        Square square1 = board.getSquares()[0][2];
        Square square2 = board.getSquares()[5][7];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved1);
        board.printBoard();
    }

    @Test
    public void testMoveWhitePlayerBishopAndKillBlackBishop() {
        Square[][] squares = super.createPieces(true, true, true, true, true, false);
        board.placePieces(squares);

        Square square1 = board.getSquares()[7][2];
        Square square2 = board.getSquares()[5][0];
        boolean moved5 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved5);
        board.printBoard();

        square1 = board.getSquares()[5][0];
        square2 = board.getSquares()[0][5];
        boolean moved6 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved6);
        board.printBoard();
    }

    @Test
    public void testMoveAround() {
        Square[][] squares = super.createPieces(true, true, true, true, true, false);
        board.placePieces(squares);

        Square square1 = board.getSquares()[0][2];
        Square square2 = board.getSquares()[5][7];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        square1 = board.getSquares()[5][7];
        square2 = board.getSquares()[6][6];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved2);
        board.printBoard();

        square1 = board.getSquares()[6][6];
        square2 = board.getSquares()[3][3];
        boolean moved3 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved3);
        board.printBoard();

        square1 = board.getSquares()[3][3];
        square2 = board.getSquares()[5][1];
        boolean moved4 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved4);
        board.printBoard();

        // Move the white player´s Bishop
        square1 = board.getSquares()[7][2];
        square2 = board.getSquares()[5][0];
        boolean moved5 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved5);
        board.printBoard();

        square1 = board.getSquares()[6][1];
        square2 = board.getSquares()[5][0];
        boolean moved6 = board.movePiece(square1, square2);
        //Assert.assertTrue("Expected true but the move failed!", moved6);
        board.printBoard();
/*
        square1 = board.getSquares()[5][0];
        square2 = board.getSquares()[4][1];
        boolean moved7 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved7);
        board.printBoard();
*/
    }
}
