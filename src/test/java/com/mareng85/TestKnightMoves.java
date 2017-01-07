package com.mareng85;

import com.mareng85.game.Square;
import org.junit.Assert;
import org.junit.Test;

public class TestKnightMoves extends BaseTest {

    @Test
    public void testMoveBlackKnight2StepsUpAnd1Right() {
        Square[][] squares = super.createPieces(true,true,true,true,true,true);
        board.placePieces(squares);

        // Move the knight 2 steps up and 1 right
        Square square1 = board.getSquares()[0][1];
        Square square2 = board.getSquares()[2][2];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();
    }

    @Test
    public void testMoveBlackKnight2StepsUpAnd1Left() {
        Square[][] squares = super.createPieces(true,true,true,true,true,true);
        board.placePieces(squares);

        // Move the knight 2 steps up and 1 left
        Square square1 = board.getSquares()[0][1];
        Square square2 = board.getSquares()[2][0];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();
    }

    @Test
    public void testMoveBlackKnight2StepsUpAnd1LeftAndBack() {
        Square[][] squares = super.createPieces(true,true,true,true,true,true);
        board.placePieces(squares);

        // Move the knight 2 steps up and 1 left
        Square square1 = board.getSquares()[0][1];
        Square square2 = board.getSquares()[2][0];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        // Move the knight back
        square1 = board.getSquares()[2][0];
        square2 = board.getSquares()[0][1];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved2);
        board.printBoard();
    }
}
