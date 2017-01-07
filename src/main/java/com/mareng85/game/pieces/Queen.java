package com.mareng85.game.pieces;

import com.mareng85.game.data.Color;

import java.awt.*;
import java.util.ArrayList;

public class Queen implements Piece {

    private Color color;
    private java.util.List<String> history;

    public Queen(Color color) {
        history = new ArrayList<>();
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        switch (color) {
            case BLACK:
                g.drawString("\u265B", 15, 45);
                break;
            case WHITE:
                g.drawString("\u2655", 15, 45);
                break;
        }
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }

    @Override
    public String getName() {
        return "Queen";
    }
}
