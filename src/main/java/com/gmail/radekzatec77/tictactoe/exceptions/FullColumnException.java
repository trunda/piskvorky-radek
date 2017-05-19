package com.gmail.radekzatec77.tictactoe.exceptions;

import java.text.MessageFormat;

/**
 * Vyjímka určující, že sloupec je plný
 */
public class FullColumnException extends Exception {
  public FullColumnException(int column) {
    super(MessageFormat.format("Sloupec {0} je plný", column));
  }
}
