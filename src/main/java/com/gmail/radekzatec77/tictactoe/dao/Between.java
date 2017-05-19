package com.gmail.radekzatec77.tictactoe.dao;

/**
 * Dva body v matici
 */
public class Between {
  public final Coordinate from;
  public final Coordinate to;

  public Between(Coordinate from, Coordinate to) {
    this.from = from;
    this.to = to;
  }

  public Between(int fColumn, int fRow, int tColumn, int tRow) {
    this(new Coordinate(fColumn, fRow), new Coordinate(tColumn, tRow));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Between between = (Between) o;

    if (!from.equals(between.from)) return false;
    return to.equals(between.to);
  }

  @Override
  public int hashCode() {
    int result = from.hashCode();
    result = 31 * result + to.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Between{" + "from=" + from + ", to=" + to + '}';
  }
}
