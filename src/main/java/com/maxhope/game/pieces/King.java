package com.maxhope.game.pieces;

import com.maxhope.game.data.Color;

import java.awt.*;
import java.util.*;
import java.util.List;

public class King implements Piece {
    private Color color;
    private java.util.List<String> history;

    public King(Color color) {
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
                g.drawString("\u265A", 15, 45);
                break;
            case WHITE:
                g.drawString("\u2654", 15, 45);
                break;
        }
    }

    @Override
    public String getName() {
        return "King";
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public String toString() {
        return getName();
    }
}
