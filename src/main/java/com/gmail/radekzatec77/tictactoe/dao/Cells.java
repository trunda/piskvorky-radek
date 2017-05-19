package com.gmail.radekzatec77.tictactoe.dao;

/**
 * Created by trunda on 19.05.17.
 */
public class Cells {
  public final Cell[] cells;

  public Cells(Cell[] cells) {
    this.cells = cells;
  }

  public Between sameInRow(int howMany) {
    int counter = 0;
    Cell last = null;
    for (int i = 0; i < cells.length; i++) {
      if (last != null && last.equals(cells[i])) {
        counter += 1;
      } else {
        counter = 1;
        last = cells[i];
      }

      if (counter == howMany) {
        return new Between(last.coordinate, cells[i].coordinate);
      }
    }

    return null;
  }
}
