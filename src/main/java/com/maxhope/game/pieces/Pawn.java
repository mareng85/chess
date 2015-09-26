package com.maxhope.game.pieces;

import com.maxhope.game.data.Color;

import java.awt.*;
import java.util.ArrayList;

public class Pawn implements Piece {

    private Color color;
    private java.util.List<String> history;

    public Pawn(Color color) {
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
                g.drawString("\u265F", 15, 45);
                break;
            case WHITE:
                g.drawString("\u2659", 15, 45);
                break;
        }
    }

    @Override
    public String getName() {
        return "Pawn";
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public String toString() {
        return getName();
    }
}
