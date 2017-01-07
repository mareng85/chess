package com.mareng85;

import com.mareng85.game.Board;
import com.mareng85.game.Square;
import com.mareng85.game.data.BoardPosition;
import com.mareng85.game.pieces.*;
import org.junit.Before;

import com.mareng85.game.data.Color;

public class BaseTest {
    protected Board board;
    private Color[] colors = new Color[]{Color.WHITE, Color.BLACK};;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    public Square[][] createPieces(boolean includeKing,
                                   boolean includeQueen,
                                   boolean includeKnights,
                                   boolean includeBishops,
                                   boolean includeRooks,
                                   boolean includePawns) {
        Square[][] squares = new Square[8][8];
        initBoard(squares);
        // Place white pieces...
        for (int i = 7; i >= 6; i--) {
            char c = 'A';
            for (int j = 0; j < 8; j++) {
                // Create rooks...
                if (includeRooks && ((i == 7 && c == 'A') || (i == 7 && c == 'H'))) {
                    Rook rook = new Rook(Color.WHITE);
                    squares[i][j].setPiece(rook);
                }

                // Create knights...
                else if (includeKnights && ((i == 7 && c == 'B') || (i == 7 && c == 'G'))) {
                    Knight knight = new Knight(Color.WHITE);
                    squares[i][j].setPiece(knight);
                }

                // Create bishops...
                else if (includeBishops && ((i == 7 && c == 'C') || (i == 7 && c == 'F'))) {
                    Bishop bishop = new Bishop(Color.WHITE);
                    squares[i][j].setPiece(bishop);
                }

                // Create queen...
                else if (includeQueen && i == 7 && c == 'D') {
                    Queen queen = new Queen(Color.WHITE);
                    squares[i][j].setPiece(queen);
                }

                // Create king...
                else if (includeKing && i == 7 && c == 'E') {
                    King king = new King(Color.WHITE);
                    squares[i][j].setPiece(king);
                }

                // Create pawns...
                else if (includePawns && i == 6) {
                    Pawn pawn = new Pawn(Color.WHITE);
                    squares[i][j].setPiece(pawn);
                }
                c++;
            }
        }

        // Place black pieces...
        for (int i = 1; i >= 0; i--) {
            char c = 'A';
            for (int j = 0; j < 8; j++) {
                // Create rooks...
                if (includeRooks && ((i == 0 && c == 'A') || (i == 0 && c == 'H'))) {
                    Rook rook = new Rook(Color.BLACK);
                    squares[i][j].setPiece(rook);
                }

                // Create knights...
                else if (includeKnights && ((i == 0 && c == 'B') || (i == 0 && c == 'G'))) {
                    Knight knight = new Knight(Color.BLACK);
                    squares[i][j].setPiece(knight);
                }

                // Create bishops...
                else if (includeBishops && ((i == 0 && c == 'C') || (i == 0 && c == 'F'))) {
                    Bishop bishop = new Bishop(Color.BLACK);
                    squares[i][j].setPiece(bishop);
                }

                // Create queen...
                else if (includeQueen && i == 0 && c == 'D') {
                    Queen queen = new Queen(Color.BLACK);
                    squares[i][j].setPiece(queen);
                }

                // Create king...
                else if (includeKing && i == 0 && c == 'E') {
                    King king = new King(Color.BLACK);
                    squares[i][j].setPiece(king);
                }

                // Create pawns...
                else if (includePawns && i == 1) {
                    Pawn pawn = new Pawn(Color.BLACK);
                    squares[i][j].setPiece(pawn);
                }
                c++;
            }
        }
        return squares;
    }

    private void initBoard(Square[][] squares) {
        for (int i = 7; i >= 0; i--) {
            char c = 'A';
            boolean color = false; // false = white, true = black
            for (int j = 0; j < 8; j++) {
                String s = Character.toString(c)+Integer.toString(i+1);
                System.out.print(s + " ");
                BoardPosition boardPosition = new BoardPosition(s, i, j);
                squares[i][j] = new Square(colors[color?0:1], boardPosition);
                color = !color;
                c++;
            }
            color = !color;
            System.out.println();
        }
    }
}
