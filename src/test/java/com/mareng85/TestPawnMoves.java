package com.mareng85;

import com.mareng85.game.Square;
import org.junit.Assert;
import org.junit.Test;

public class TestPawnMoves extends BaseTest{

    @Test
    public void testMovePawnOneStep() throws Exception {
        board.printBoard();
        Square square1 = board.getSquares()[1][1];
        Square square2 = board.getSquares()[2][1];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved);
        board.printBoard();
    }

    @Test
    public void testMovePawnTwoSteps() throws Exception {
        board.printBoard();
        Square square1 = board.getSquares()[1][1];
        Square square2 = board.getSquares()[3][1];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved);
        board.printBoard();
    }

    @Test
    public void testMovePawnTwoStepsDown() throws Exception {
        board.printBoard();
        Square square1 = board.getSquares()[6][6];
        Square square2 = board.getSquares()[4][6];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved);
        board.printBoard();
    }

    @Test
    public void testMovePawnThreeSteps() throws Exception {
        board.printBoard();
        Square square1 = board.getSquares()[1][1];
        Square square2 = board.getSquares()[4][1];
        boolean moved = board.movePiece(square1, square2);
        Assert.assertFalse("Expected false but the move succeeded but should fail!", moved);
        board.printBoard();
    }

    @Test
    public void testMovePawnAndKillEnemy() {
        Square square1 = board.getSquares()[6][1];
        Square square2 = board.getSquares()[4][1];
        boolean moved1 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved1);
        board.printBoard();

        square1 = board.getSquares()[1][2];
        square2 = board.getSquares()[3][2];
        boolean moved2 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved2);
        board.printBoard();

        square1 = board.getSquares()[4][1];
        square2 = board.getSquares()[3][2];
        boolean moved3 = board.movePiece(square1, square2);
        Assert.assertTrue("Expected true but the move failed!", moved3);
        board.printBoard();
    }


}
