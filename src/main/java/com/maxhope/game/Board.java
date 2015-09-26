package com.maxhope.game;


import com.maxhope.game.data.*;
import com.maxhope.game.pieces.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {

    private Square[][] squares = new Square[8][8];
    private Color[] colors = new Color[]{Color.WHITE,Color.BLACK};

    public Board() {
        initBoard();
        placePieces();
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSquare(int row, int column) {
        //TODO check that row=[0-7] and column=[0-7]
        Square square = squares[row][column];
        return square;
    }

    private void initBoard() {
        boolean color = false; // false = white, true = black
        for (int i = 7; i >= 0; i--) {
            char c = 'A';
            for (int j = 0; j < 8; j++) {
                String s = Character.toString(c)+Integer.toString(i+1);
                BoardPosition boardPosition = new BoardPosition(s, i, j);
                squares[i][j] = new Square(colors[color?0:1], boardPosition);
                color = !color;
                c++;
            }
            color = !color;
        }
    }

    public void placePieces(Square[][] squares) {
        this.squares = squares;
    }

    private void placePieces() {
        // Place white pieces...
        for (int i = 7; i >= 6; i--) {
            char c = 'A';
            for (int j = 0; j < 8; j++) {
                // Create rooks...
                if ((i == 7 && c == 'A') || (i == 7 && c == 'H')) {
                    Rook rook = new Rook(Color.BLACK);
                    squares[i][j].setPiece(rook);
                }

                // Create knights...
                else if ((i == 7 && c == 'B') || (i == 7 && c == 'G')) {
                    Knight knight = new Knight(Color.BLACK);
                    squares[i][j].setPiece(knight);
                }

                // Create bishops...
                else if ((i == 7 && c == 'C') || (i == 7 && c == 'F')) {
                    Bishop bishop = new Bishop(Color.BLACK);
                    squares[i][j].setPiece(bishop);
                }

                // Create queen...
                else if (i == 7 && c == 'D') {
                    Queen queen = new Queen(Color.BLACK);
                    squares[i][j].setPiece(queen);
                }

                // Create king...
                else if (i == 7 && c == 'E') {
                    King king = new King(Color.BLACK);
                    squares[i][j].setPiece(king);
                }

                // Create pawns...
                else if (i == 6) {
                    Pawn pawn = new Pawn(Color.BLACK);
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
                if ((i == 0 && c == 'A') || (i == 0 && c == 'H')) {
                    Rook rook = new Rook(Color.WHITE);
                    squares[i][j].setPiece(rook);
                }

                // Create knights...
                else if ((i == 0 && c == 'B') || (i == 0 && c == 'G')) {
                    Knight knight = new Knight(Color.WHITE);
                    squares[i][j].setPiece(knight);
                }

                // Create bishops...
                else if ((i == 0 && c == 'C') || (i == 0 && c == 'F')) {
                    Bishop bishop = new Bishop(Color.WHITE);
                    squares[i][j].setPiece(bishop);
                }

                // Create qeen...
                else if (i == 0 && c == 'D') {
                    Queen queen = new Queen(Color.WHITE);
                    squares[i][j].setPiece(queen);
                }

                // Create king...
                else if (i == 0 && c == 'E') {
                    King king = new King(Color.WHITE);
                    squares[i][j].setPiece(king);
                }

                // Create pawns...
                else if (i == 1) {
                    Pawn pawn = new Pawn(Color.WHITE);
                    squares[i][j].setPiece(pawn);
                }
                c++;
            }
        }
    }

    public boolean movePiece(Square fromSquare, Square toSquare) {
        boolean leagal, moved = false;

        leagal = isLeagalMove(fromSquare, toSquare);

        if (leagal) {
            Piece piece = fromSquare.getPiece();
            fromSquare.setPiece(null);
            toSquare.setPiece(piece);
            moved = leagal;
        }
        return moved;
    }

    public boolean isLeagalMove(Square oldSquare, Square newSquare) {
        boolean leagal = false;
        Piece piece = oldSquare.getPiece();
        String oldPosition = oldSquare.getBoardPosition().getPositionName();
        String newPosition = newSquare.getBoardPosition().getPositionName();
        if (piece == null)
            return false;

        // Old position
        char oa = oldPosition.charAt(0);
        int oi = Integer.parseInt(oldPosition.substring(1))-1;

        // New position
        char na = newPosition.charAt(0);
        int ni = Integer.parseInt(newPosition.substring(1))-1;

        switch (piece.getType()) {
            case BISHOP:
                outer:for (int i = 0; i < 8; i++) {
                    boolean l = false;
                    // Regular move, steps up, down, left, right or diagonally in any direction
                    if (oi + i + 1 == ni && (char) (oa + 1 + i) == na) { // Diagonally up right for white player or diagonally down left for black player
                        // Check the path to new square for own pieces that are blocking the way...
                        int y = 0;
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char) (oa + y)) + Integer.toString(oi + 1 + j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if (p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... " + p + " collides with " + piece + " at position " + Character.toString(oa) + Integer.toString(oi + 1 + j));
                                break outer;
                            }
                            if (y < na)
                                y++;
                        }
                        l = true;
                    } else if (oi + i + 1 == ni && oa - 1 - i == na) { // Diagonally up left for white player or diagonally down right for black player
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < (oa - na); j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char) (oa - j)) + Integer.toString(oi + 1 + j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if (p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... " + p + " collides with " + piece + " at position " + Character.toString((char) (oa - j)) + Integer.toString(oi + 1 + j));
                                break outer;
                            }
                        }
                        l = true;
                    } else if (oi - i == ni && oa + i == na) { // Diagonally down right
                        // Check the path to new square for own pieces that are blocking the way...
                        int y = 0;
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char) (oa + y)) + Integer.toString(oi + 1 - j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if (p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... " + p + " collides with " + piece + " at position " + Character.toString(oa) + Integer.toString(oi + 1 + j));
                                break outer;
                            }
                            if (y < na)
                                y++;
                        }
                        l = true;
                    } else if (oi - i == ni && oa - i == na) { // Diagonally down left
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < (oa - na); j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char) (oa - j)) + Integer.toString(oi + 1 - j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if (p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... " + p + " collides with " + piece + " at position " + Character.toString((char) (oa - j)) + Integer.toString(oi + 1 - j));
                                break outer;
                            }
                        }
                        l = true;
                    }
                    if(l && (newSquare.getPiece() == null  ||
                            (newSquare.getPiece() != null &&  !newSquare.getPiece().getColor().equals(piece.getColor())))) {
                        leagal = true;
                    }
                }
                break;
            case KING:
                // Regular move, one step up, down, left, right or diagonally in any direction
                System.out.println("Moving king from "+oldSquare.getBoardPosition().getPositionName()+" to "+newSquare.getBoardPosition().getPositionName());
                if (((oi+1 == ni && oa+1 == na) || // Diagonally up right
                    (oi+1 == ni && oa-1 == na)  || // Diagonally up left
                    (oi-1 == ni && oa+1 == na)  || // Diagonally down right
                    (oi-1 == ni && oa-1 == na)  || // Diagonally down left
                    (oi+1 == ni && oa == na)    || // Up
                    (oi-1 == ni && na == oa)    || // Down
                    (oi == ni && na == oa+1)    || // Left
                    (oi == ni && na == oa-1))   && // Right
                    (newSquare.getPiece() == null) ||
                    (newSquare.getPiece() != null &&  !newSquare.getPiece().getColor().equals(piece.getColor()))) {
                    leagal = true;
                }
                break;
            case KNIGHT:

                boolean l = false;
                // Regular move, two squares up and one right
                if (oi+2 == ni && na == oa+1) {
                    l = true;
                }
                // Regular move, two squares up and one left
                else if(oi+2 == ni && na == oa-1) {
                    l = true;
                }
                // Regular move, two down up and one right
                else if (oi-2 == ni && na == oa+1) {
                    l = true;
                }
                // Regular move, two down up and one left
                else if(oi-2 == ni && na == oa-1) {
                    l = true;
                }
                // Test if killing enemy piece
                if (l && newSquare.getPiece() == null|| (newSquare.getPiece() != null && !newSquare.getPiece().getColor().equals(piece.getColor()))) {
                    leagal = true;
                }
                break;
            case PAWN:
                // Regular move, one square up
                if (oi+1 == ni && na == oa && newSquare.getPiece() == null) {
                    leagal = true;
                }
                // First move, 2 squares up
                else if(oi == 1 && oi+2 == ni && na == oa && newSquare.getPiece() == null) {
                    leagal = true;
                }
                // Regular move, one square down
                else if (oi-1 == ni && na == oa && newSquare.getPiece() == null) {
                    leagal = true;
                }
                // First move, 2 squares down
                else if(oi == 6 && oi-2 == ni && na == oa && newSquare.getPiece() == null) {
                    leagal = true;
                }
                // Move diagonally up only if killing enemy piece
                else if (oi+1 == ni && (na == oa-1 || na == oa+1) && newSquare.getPiece() != null && !newSquare.getPiece().getColor().equals(piece.getColor())) {
                    System.out.println(piece.getName()+ " killed " + newSquare.getPiece().getName());
                    leagal = true;
                }
                // Move diagonally down only if killing enemy piece
                else if (oi-1 == ni && (na == oa-1 || na == oa+1) && newSquare.getPiece() != null && !newSquare.getPiece().getColor().equals(piece.getColor())) {
                    System.out.println(piece.getName()+ " killed " + newSquare.getPiece().getName());
                    leagal = true;
                }
                break;
            case QUEEN:
                outer:for (int i = 0; i < 8; i++) {
                    l = false;
                    // Regular move, steps up, down, left, right or diagonally in any direction
                    if (oi+i+1 == ni && (char)(oa+1+i) == na) { // Diagonally up right
                        // Check the path to new square for own pieces that are blocking the way...
                        int y = 0;
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char)(oa+y))+Integer.toString(oi+1+j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString(oa)+Integer.toString(oi+1+j));
                                break outer;
                            }
                            if(y < na)
                                y++;
                        }
                        l = true;
                    }
                    else if (oi+i+1 == ni && oa-1-i == na) { // Diagonally up left
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < (oa-na); j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char)(oa-j))+Integer.toString(oi+1+j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString((char)(oa-j))+Integer.toString(oi+1+j));
                                break outer;
                            }
                        }
                        l = true;
                    }
                    else if (oi-i == ni && oa+i == na) { // Diagonally down right
                        // Check the path to new square for own pieces that are blocking the way...
                        int y = 0;
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char)(oa+y))+Integer.toString(oi+1-j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString(oa)+Integer.toString(oi+1+j));
                                break outer;
                            }
                            if(y < na)
                                y++;
                        }
                        l = true;
                    }
                    else if (oi-i == ni && oa-i == na) { // Diagonally down left
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < (oa-na); j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char)(oa-j))+Integer.toString(oi+1-j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString(oa)+Integer.toString(oi+1-j));
                                break outer;
                            }
                        }
                        l = true;
                    }
                    else if (oi+i+1 == ni && oa == na) { // Up
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString(oa)+Integer.toString(oi+1+j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString(oa)+Integer.toString(oi+1+j));
                                break outer;
                            }
                        }
                        l = true;
                    }
                    else if (oi-i+1 == ni && oa == na) { // Down
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString(oa)+Integer.toString(oi+1-j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString(oa)+Integer.toString(oi+1-j));
                                break outer;
                            }
                        }
                        l = true;
                    }
                    else if (oi == ni && na == oa-1) { // Left
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char)(oa-j))+Integer.toString(oi+1));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString((char)(oa-j))+Integer.toString(oi+1));
                                break outer;
                            }
                        }
                        l = true;
                    }
                    else if (oi == ni && na == oa+1){ // Right
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char)(oa+j))+Integer.toString(oi+1));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString((char)(oa+j))+Integer.toString(oi+1));
                                break outer;
                            }
                        }
                        l = true;
                    }
                    if(l && (newSquare.getPiece() == null  ||
                            (newSquare.getPiece() != null &&  !newSquare.getPiece().getColor().equals(piece.getColor())))) {
                        leagal = true;
                        System.out.println("leagal = "+leagal);
                    }
                }
                break;
            case ROOK:
                outer:for (int i = 0; i < 8; i++) {
                    if (oi+1+i == ni && na == oa && newSquare.getPiece() == null || (newSquare.getPiece() != null && !newSquare.getPiece().getColor().equals(piece.getColor()))) {  // If moving up
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString(oa)+Integer.toString(oi+1+j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString(oa)+Integer.toString(oi+1+j));
                                break outer;
                            }
                        }
                        leagal = true;
                        break;
                    } else if (oi-1-i == ni && na == oa && newSquare.getPiece() == null || (newSquare.getPiece() != null && !newSquare.getPiece().getColor().equals(piece.getColor()))) {  // If moving down
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < (oa - na); j++) {
                            int[] pos = getPositionOnBoard(Character.toString(oa)+Integer.toString(oi+1-j));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString(oa)+Integer.toString(oi+1-j));
                                break outer;
                            }
                        }
                        leagal = true;
                        break;
                    } else if (oi == ni && na == oa+i && newSquare.getPiece() == null || (newSquare.getPiece() != null && !newSquare.getPiece().getColor().equals(piece.getColor()))) {  // If moving right
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char)(oa+j))+Integer.toString(oi+1));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString((char)(oa+j))+Integer.toString(oi+1));
                                break outer;
                            }
                        }
                        leagal = true;
                        break;
                    } else if (oi == ni && na == oa-i && newSquare.getPiece() == null || (newSquare.getPiece() != null && !newSquare.getPiece().getColor().equals(piece.getColor()))) {  // If moving left
                        // Check the path to new square for own pieces that are blocking the way...
                        for (int j = 0; j < ni; j++) {
                            int[] pos = getPositionOnBoard(Character.toString((char)(oa-j))+Integer.toString(oi+1));
                            Piece p = squares[pos[0]][pos[1]].getPiece();
                            if(p != null && !p.equals(piece) && p.getColor().equals(piece.getColor())) {
                                System.out.println("break... "+p+ " collides with "+piece + " at position "+Character.toString((char)(oa-j))+Integer.toString(oi+1));
                                break outer;
                            }
                        }
                        leagal = true;
                        break;
                    } else if (newSquare.getPiece() != null && newSquare.getPiece().getColor().equals(piece.getColor())) {
                        break;
                    }
                }
                break;
            default:
                throw new RuntimeException("Should not happen!");
        }
        return leagal;
    }

    public void printBoard() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                String pieceName;
                if (squares[i][j].getPiece() != null) {
                    pieceName = squares[i][j].getPiece().getName();
                } else {
                    pieceName = "[  ]";
                }

                System.out.print(pieceName + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[] getPositionOnBoard(String position) {
        position = position.substring(0,2);

        Pattern pattern = Pattern.compile("[a-hA-H1-8]{2}");
        Matcher matcher = pattern.matcher(position);
        boolean found = matcher.find();
        if (!found) {
            throw new RuntimeException(position + " is an invalid position!");
        }

        char character = position.charAt(0);
        int integer = Integer.parseInt(position.substring(1));

        int a = 0;

        if(character == 'A')
            a = 0;
        else if(character == 'B')
            a = 1;
        else if(character == 'C')
            a = 2;
        else if(character == 'D')
            a = 3;
        else if(character == 'E')
            a = 4;
        else if(character == 'F')
            a = 5;
        else if(character == 'G')
            a = 6;
        else if(character == 'H')
            a = 7;
        return new int[]{integer-1, a}; // -1 for we are storing 1-8 in the position values
    }
}
