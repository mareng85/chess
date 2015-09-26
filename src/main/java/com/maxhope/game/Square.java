package com.maxhope.game;

import com.maxhope.game.data.BoardPosition;
import com.maxhope.game.data.Color;
import com.maxhope.game.pieces.Piece;

public class Square {

    private Color color;
    private BoardPosition boardPosition;
    private Piece piece;

    public Square(Color color, BoardPosition boardPosition) {
        this.color = color;
        this.boardPosition = boardPosition;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Color getColor() {
        return color;
    }

    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (color != null ? !color.equals(square.color) : square.color != null) return false;
        if (boardPosition != null ? !boardPosition.equals(square.boardPosition) : square.boardPosition != null)
            return false;
        return !(piece != null ? !piece.equals(square.piece) : square.piece != null);

    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (boardPosition != null ? boardPosition.hashCode() : 0);
        result = 31 * result + (piece != null ? piece.hashCode() : 0);
        return result;
    }
}
