package com.mareng85.game.pieces;

import com.mareng85.game.data.Color;

import java.awt.*;
import java.util.ArrayList;

public class Knight implements Piece {

    private Color color;
    private java.util.List<String> history;

    public Knight(Color color) {
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
                g.drawString("\u265E", 15, 45);
                break;
            case WHITE:
                g.drawString("\u2658", 15, 45);
                break;
        }
    }

    @Override
    public String getName() {
        return "Knight";
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    @Override
    public String toString() {
        return getName();
    }
}
