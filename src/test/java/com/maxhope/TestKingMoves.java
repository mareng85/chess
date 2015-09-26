package com.maxhope;

import com.maxhope.game.Square;
import org.junit.Assert;
import org.junit.Test;

public class TestKingMoves extends BaseTest {

    @Test
     public void testMoveKingOneLeftOnStart() {
        board.printBoard();
        Square square1 = board.getSquares()[0][4];
        Square square2 = board.getSquares()[0][3];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved);
        board.printBoard();
    }

    @Test
    public void testMoveKingOneUp() {
        // Move away the above pawn so we can move the king...
        Square square1 = board.getSquares()[1][4];
        Square square2 = board.getSquares()[2][4];
        board.movePiece(square1, square2);
        board.printBoard();

        // Move the king...
        square1 = board.getSquares()[0][4];
        square2 = board.getSquares()[1][4];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved);
        board.printBoard();
    }

    @Test
    public void testIllegalMove() {
        // Move away the above pawn so we can move the king...
        Square square1 = board.getSquares()[1][4];
        Square square2 = board.getSquares()[2][4];
        board.movePiece(square1, square2);
        board.printBoard();

        // Move the king...
        square1 = board.getSquares()[0][4];
        square2 = board.getSquares()[1][3];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded!", moved);
        board.printBoard();
    }
}
