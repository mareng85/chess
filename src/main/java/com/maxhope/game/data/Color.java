package com.maxhope.game.data;

public enum Color {
    BLACK(java.awt.Color.BLACK),
    WHITE(java.awt.Color.WHITE);

    private java.awt.Color color;

    Color(java.awt.Color color) {
        this.color = color;
    }

    public java.awt.Color getAWTColor() {
        return color;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
