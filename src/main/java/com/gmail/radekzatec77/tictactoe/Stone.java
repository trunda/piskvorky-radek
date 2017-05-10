package com.gmail.radekzatec77.tictactoe;

public class Stone {

  public enum Color {
    WHITE, BLACK
  }

  private final int column;
  private final int row;
  private final Color color;

  public Stone(int column, int row, Color color) {
    this.column = column;
    this.row = row;
    this.color = color;
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  public Color getColor() {
    return color;
  }

  public boolean isSameColor(Stone stone) {
    return stone != null && stone.color.equals(color);
  }
}
