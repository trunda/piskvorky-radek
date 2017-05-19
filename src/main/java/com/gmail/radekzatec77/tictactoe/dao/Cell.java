package com.gmail.radekzatec77.tictactoe.dao;


import com.gmail.radekzatec77.tictactoe.StoneColor;

/**
 * Buňka v herní matici
 */
public class Cell {
  public final Coordinate coordinate;
  public final StoneColor stone;

  public Cell(Coordinate coordinate, StoneColor stone) {
    this.coordinate = coordinate;
    this.stone = stone;
  }

  public Cell(int column, int row, StoneColor stone) {
    this(new Coordinate(column, row), stone);
  }

  @Override
  public String toString() {
    return "Cell{" + "coordinate=" + coordinate + ", stone=" + stone + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Cell cell = (Cell) o;

    if (stone != null) {
      return stone.equals(cell.stone);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    if (stone != null) {
      return stone.hashCode();
    } else {
      return 0;
    }
  }
}
