package com.gmail.radekzatec77.tictactoe;

import com.gmail.radekzatec77.collections.Matrix;
import com.gmail.radekzatec77.tictactoe.resolver.OffsetArrayIterator;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.List;

/**
 * This class resolves TicTacToe game
 */
public class Resolver {
  private final int inRowCount;

  /**
   * Constructor
   * @param inRowCount How many stones has to be in row
   */
  public Resolver(int inRowCount) {
    this.inRowCount = inRowCount;
  }

  /**
   * Check if the last stone placement indicates winner
   *
   * @param board
   * @param column
   * @param row
   * @return
   */
  public Stone.Color resolve(Matrix<Stone> board, int column, int row) {
    // 1. check column
    Stone winner = isInRow(getColumnIterable(board, column, row, inRowCount), inRowCount);

    if (winner != null) {
      return winner.getColor();
    }
    // 1. check row
    winner = isInRow(getRowIterable(board, row, column, inRowCount), inRowCount);
    if (winner != null) {
      return winner.getColor();
    }

    // 3. check diagonals
    winner = isInRow(getLeftDiagIterable(board, column, row, column, inRowCount), inRowCount);
    if (winner != null) {
      return winner.getColor();
    }
    winner = isInRow(getRightDiagIterable(board, column, row, column, inRowCount), inRowCount);
    if (winner != null) {
      return winner.getColor();
    }

    return null;
  }

  private Iterable<Stone> getIterable(List<Stone> list, int start, int offset) {
    Stone[] array = new Stone[list.size()];
    array = list.toArray(array);
    return new OffsetArrayIterator<Stone>(array, start, offset);
  }

  private Iterable<Stone> getRowIterable(Matrix<Stone> board, int row, int start, int offset) {
    return getIterable(board.row(row), start, offset);
  }

  private Iterable<Stone> getColumnIterable(Matrix<Stone> board, int column, int start, int offset) {
    return getIterable(board.col(column), start, offset);
  }

  private Iterable<Stone> getLeftDiagIterable(Matrix<Stone> board, int column, int row, int start, int offset) {
    return getIterable(board.leftDiagonal(column, row), start, offset);
  }

  private Iterable<Stone> getRightDiagIterable(Matrix<Stone> board, int column, int row, int start, int offset) {
    return getIterable(board.rightDiagonal(column, row), start, offset);
  }

  private Stone isInRow(Iterable<Stone> stones, int size) {
    Stone last = null;
    int count = 0;
    for (Stone current: stones) {
      if (last != null && last.isSameColor(current)) {
        count++;
      } else {
        count = 1;
        last = current;
      }

      if (count == size) {
        return last;
      }
    }

    return null;
  }

}
