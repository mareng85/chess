package com.maxhope.game.pieces;


import com.maxhope.game.data.Color;

import java.awt.*;

public interface Piece {
    public Color getColor();
    public String getName();
    public PieceType getType();
    public void draw(Graphics g);
}
