package com.gmail.radekzatec77.tictactoe.dao;

/**
 * Diagonály herní matice
 */
public class Diagonals {
  public final Cells left;
  public final Cells right;

  public Diagonals(Cells left, Cells right) {
    this.left = left;
    this.right = right;
  }
}
