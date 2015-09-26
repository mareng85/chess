package com.maxhope.game.pieces;

import com.maxhope.game.data.Color;

import java.awt.*;
import java.util.ArrayList;

public class Rook implements Piece {
    private Color color;
    private java.util.List<String> history;

    public Rook(Color color) {
        history = new ArrayList<>();
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        switch (color) {
            case BLACK:
                g.drawString("\u265C", 15, 45);
                break;
            case WHITE:
                g.drawString("\u2656", 15, 45);
                break;
        }
    }

    @Override
    public String getName() {
        return "Rook";
    }

    @Override
    public String toString() {
        return getName();
    }
}
