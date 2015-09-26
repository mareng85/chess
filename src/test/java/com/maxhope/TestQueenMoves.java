package com.maxhope;

import com.maxhope.game.Square;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TestQueenMoves extends BaseTest {
    @Test
    public void testMoveQueenOneLeftOnStart() {
        board.printBoard();
        Square square1 = board.getSquares()[0][3];
        Square square2 = board.getSquares()[0][2];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved);
        board.printBoard();
    }

    @Test
    public void testMoveQueenTwoUp() {
        // Move away the above pawn so we can move the king...
        Square square1 = board.getSquares()[1][3];
        Square square2 = board.getSquares()[3][3];
        board.movePiece(square1, square2);
        board.printBoard();

        // Move the queen...
        square1 = board.getSquares()[0][3];
        square2 = board.getSquares()[2][3];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved);
        board.printBoard();
    }

    @Test
    public void testMoveQueenFourUp() {
        // Move away the above pawn so we can move the king...
        Square square1 = board.getSquare(1,3);
        Square square2 = board.getSquare(3,3);
        board.movePiece(square1, square2);
        board.printBoard();

        // Move the queen...
        square1 = board.getSquare(0,3);
        square2 = board.getSquare(4,3);
        boolean moved = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved);
        board.printBoard();
    }

    @Test
    public void testMoveOneRightDiagonallyUp() {
        // Move away the above pawn so we can move the king...
        Square square1 = board.getSquare(1,4);
        Square square2 = board.getSquare(3,4);
        board.movePiece(square1, square2);
        board.printBoard();

        // Move the queen...
        square1 = board.getSquare(0,3);
        square2 = board.getSquare(1,4);
        boolean moved = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved);
        board.printBoard();
    }

    @Test
    public void testMoveFourRightDiagonallyUp() {
        // Move away the above pawn so we can move the king...
        Square square1 = board.getSquare(1,4);
        Square square2 = board.getSquare(2,4);
        board.movePiece(square1, square2);

        board.printBoard();

        // Move the queen...
        square1 = board.getSquares()[0][3];
        square2 = board.getSquares()[4][7];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved);
        board.printBoard();
    }

    @Test
    public void testMoveOneRightDiagonallyUpAndFail() {
        // Move the queen...
        Square square1 = board.getSquare(0,3);
        Square square2 = board.getSquare(3,6);
        boolean moved = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved);
        board.printBoard();
    }

    @Test
    public void testMoveTwoRightDiagonallyUpAndBack() {
        Square[][] squares = super.createPieces(false,true,false,false,false,false);
        board.placePieces(squares);

        // Move the queen diagonally 2 up right
        Square square1 = board.getSquares()[0][3];
        Square square2 = board.getSquares()[2][5];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        // Move the queen diagonally 2 down left
        square1 = board.getSquares()[2][5];
        square2 = board.getSquares()[0][3];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved2);
        board.printBoard();
    }

    @Test
    public void testMoveTwoLeftDiagonallyUpAndBack() {
        Square[][] squares = super.createPieces(false,true,false,false,false,false);
        board.placePieces(squares);

        // Move the queen diagonally 2 up left
        Square square1 = board.getSquares()[0][3];
        Square square2 = board.getSquares()[3][0];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        // Move the queen diagonally 2 down right
        square1 = board.getSquares()[3][0];
        square2 = board.getSquares()[0][3];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved2);
        board.printBoard();
    }

    @Test
    public void testMoveTwoLeftDiagonallyUpAndFail() {
        Square[][] squares = super.createPieces(true,true,true,true,true,true);
        board.placePieces(squares);

        // Move the queen diagonally 2 up left
        Square square1 = board.getSquares()[0][3];
        Square square2 = board.getSquares()[3][0];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved1);
        board.printBoard();
    }

    @Test
    public void testMoveLeftDiagonallyDown() {
        Square[][] squares = super.createPieces(true, true, false, false, false, false);
        board.placePieces(squares);

        // Move the queen diagonally 1 up left
        Square square1 = board.getSquares()[0][3];
        Square square2 = board.getSquares()[1][2];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        // Move the queen diagonally 1 down left
        square1 = board.getSquares()[1][2];
        square2 = board.getSquares()[0][1];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved2);
        board.printBoard();
    }

    @Test
    public void testMoveTwoLeftDiagonallyDown() {
        Square[][] squares = super.createPieces(true, true, true, false, false, false);
        board.placePieces(squares);

        // Move the queen diagonally 1 up left
        Square square1 = board.getSquares()[0][3];
        Square square2 = board.getSquares()[1][2];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        // Move the queen diagonally 1 down left and collide with Knight
        square1 = board.getSquares()[1][2];
        square2 = board.getSquares()[0][1];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved2);
        board.printBoard();
    }

    @Test
    public void testMoveTwoLeftDiagonallyDownAndCollideWithPawn() {
        Square[][] squares = super.createPieces(true, true, true, false, false, true);
        board.placePieces(squares);

        // Move the queen diagonally 1 up left
        Square square1 = board.getSquares()[1][2];
        Square square2 = board.getSquares()[3][2];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        square1 = board.getSquares()[1][0];
        square2 = board.getSquares()[2][0];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved2);
        board.printBoard();

        // Move the queen diagonally 2 up left
        square1 = board.getSquares()[0][3];
        square2 = board.getSquares()[1][2];
        boolean moved3 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved3);
        board.printBoard();

        // Move the queen diagonally 1 down left and collide with Knight
        square1 = board.getSquares()[1][2];
        square2 = board.getSquares()[0][1];
        boolean moved4 = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved4);
        board.printBoard();
    }

    @Test
    public void testMoveAroundQueen() {
        Square[][] squares = super.createPieces(false,true,false,false,false,false);
        board.placePieces(squares);

        // Move the queen diagonally 1 up right
        Square square1 = board.getSquares()[0][3];
        Square square2 = board.getSquares()[1][4];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        // Move the queen diagonally 3 up rith
        square1 = board.getSquares()[1][4];
        square2 = board.getSquares()[4][7];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved2);
        board.printBoard();

        // Move the queen diagonally 2 up left
        square1 = board.getSquares()[4][7];
        square2 = board.getSquares()[6][5];
        boolean moved3 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved3);
        board.printBoard();

        // Move the queen diagonally 2 down left
        square1 = board.getSquares()[6][5];
        square2 = board.getSquares()[4][3];
        boolean moved4 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved4);
        board.printBoard();

        // Move down queen to original square
        square1 = board.getSquares()[4][3];
        square2 = board.getSquares()[0][3];
        boolean moved5 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved5);
        board.printBoard();

        squares = super.createPieces(false,true,false,true,false,false);
        board.placePieces(squares);

        // Move queen to right
        square1 = board.getSquares()[0][3];
        square2 = board.getSquares()[0][6];
        boolean moved6 = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved6);
        board.printBoard();

        // Move queen to left
        square1 = board.getSquares()[0][3];
        square2 = board.getSquares()[0][1];
        boolean moved7 = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved7);
        board.printBoard();
    }

    @Test
    public void testIllegalMoves() {
        // Move the queen one up...
        Square square1 = board.getSquares()[0][3];
        Square square2 = board.getSquares()[1][3];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved1);
        board.printBoard();

        // Move the queen 6 up...
        square1 = board.getSquares()[0][3];
        square2 = board.getSquares()[3][3];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved2);
        board.printBoard();
    }
}
