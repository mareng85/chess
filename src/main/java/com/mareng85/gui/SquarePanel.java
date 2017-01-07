package com.mareng85.gui;

import com.mareng85.game.pieces.Piece;
import com.mareng85.game.Square;
import com.mareng85.game.data.Color;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {

    private Square square;

    public SquarePanel(Square square) {
        this.square = square;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(square.getColor().getAWTColor());
        g2.fillRect(0, 0, getWidth(), getHeight());

        if(square.getPiece() != null) {
            Piece piece = square.getPiece();
            if(piece.getColor() == Color.WHITE) {
                g2.setColor(java.awt.Color.CYAN);
            }
            else {
                g2.setColor(java.awt.Color.MAGENTA);
            }
            piece.draw(g2);
        }
    }

    public Square getSquare() {
        return square;
    }
}
