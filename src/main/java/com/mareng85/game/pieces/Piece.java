package com.mareng85.game.pieces;


import com.mareng85.game.data.Color;

import java.awt.*;

public interface Piece {
    public Color getColor();
    public String getName();
    public PieceType getType();
    public void draw(Graphics g);
}
