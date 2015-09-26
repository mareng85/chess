package com.maxhope.game.data;

import com.maxhope.game.util.NullCheck;

public class BoardPosition {

    private int row;
    private int column;
    private String positionName;

    public BoardPosition(String positionName, int row, int column) {
        NullCheck.check("Parameters cannot be null!", new Object[]{positionName, row, column});
        this.positionName = positionName;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getPositionName() {
        return positionName;
    }

    @Override
    public String toString() {
        return getPositionName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardPosition that = (BoardPosition) o;

        if (row != that.row) return false;
        return column == that.column;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
