package com.gmail.radekzatec77.tictactoe.dao;

/**
 * Souřadnice v herní matici
 */
public class Coordinate {
  /**
   * Sloupec
   */
  public final int column;
  /**
   * Souřadnice
   */
  public final int row;

  /**
   * Konstruktor
   * @param column
   * @param row
   */
  public Coordinate(int column, int row) {
    this.column = column;
    this.row = row;
  }

  /**
   * Jsou koordináty stejné
   * @param o
   * @return
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Coordinate that = (Coordinate) o;

    if (column != that.column) return false;
    return row == that.row;
  }

  /**
   * Hashovací funkce
   * @return
   */
  @Override
  public int hashCode() {
    int result = column;
    result = 31 * result + row;
    return result;
  }

  /**
   * Pro výpis
   * @return
   */
  @Override
  public String toString() {
    return "Coordinate{" + "column=" + column + ", row=" + row + '}';
  }

}
