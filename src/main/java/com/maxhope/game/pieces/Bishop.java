package com.maxhope.game.pieces;

import com.maxhope.game.data.Color;

import java.awt.*;
import java.util.ArrayList;

public class Bishop implements Piece {

    private Color color;
    private java.util.List<String> history;

    public Bishop(Color color) {
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
                g.drawString("\u265D", 15, 45);
                break;
            case WHITE:
                g.drawString("\u2657", 15, 45);
                break;
        }
    }

    @Override
    public String getName() {
        return "Bishop";
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    @Override
    public String toString() {
        return getName();
    }
}
