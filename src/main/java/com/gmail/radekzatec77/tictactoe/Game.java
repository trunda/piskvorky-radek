package com.gmail.radekzatec77.tictactoe;

import com.gmail.radekzatec77.collections.Matrix;

public class Game {
  private int width;
  private int height;
  private Matrix<Stone> stones;
  private static int WIN_COUNT = 4;
  private final Resolver resolver;
  private Stone lastStone = null;
  private Stone.Color winner = null;

  public Game(int width, int height) {
    this.width = width;
    this.height = height;
    this.resolver = new Resolver(WIN_COUNT);
    reset();
  }

  /**
   * Width getter
   * @return width
   */
  public int getWidth() {
    return width;
  }

  public void reset() {
    stones = new Matrix<Stone>(width, height);
    lastStone = null;
    winner = null;
  }

  /**
   * Height getter
   * @return height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Set the stone on first free row in given column
   * @param column Column to be set
   * @param color Color to be set
   * @return Array of coordinates
   * @throws GameException If there is no free rows in column
   */
  public int[] setStone(int column, Stone.Color color) throws GameException {
    return this.setStone(column, getFirstFreeRow(column), color);
  }

  /**
   * Set the stone on exact coordinates
   * @param column Column
   * @param row Row
   * @param color Color
   * @return Self - chaining design pattern
   * @throws GameException If there is already a stone on given coordinates
   */
  public int[] setStone(int column, int row, Stone.Color color) throws GameException {
    if (winner != null) {
      throw new GameException(String.format("This game is already finished, %s WON!", winner));
    }

    if (stones.get(column, row) != null) {
      throw new GameException(String.format("Coordinates [%d, %d] already contains stone!", column, row));
    }

    stones.set(column, row, lastStone = new Stone(column, row, color));

    return new int[] { column, row };
  }

  public Stone.Color getWinner() {
    if (winner == null && lastStone != null) {
      winner = resolver.resolve(stones, lastStone.getColumn(), lastStone.getRow());
    }

    return winner;
  }

  /**
   * Returns stone on given coordinates
   * @param column
   * @param row
   * @return Color of the stone
   */
  public Stone getStone(int column, int row) {
    return this.stones.get(column, row);
  }


  /**
   * Returns first free row of given column
   *
   * @param column Column
   * @return Free row index
   * @throws GameException If there is no free row (column is full)
   */
  private Integer getFirstFreeRow(int column) throws GameException {
    Stone currentStone;
    int row = 0;
    do {
      if (row == getHeight()) {
        throw new GameException(String.format("Column [%s] is full", column));
      }
      currentStone = getStone(column, row++);
    } while (currentStone != null);

    return row - 1;
  }

}
