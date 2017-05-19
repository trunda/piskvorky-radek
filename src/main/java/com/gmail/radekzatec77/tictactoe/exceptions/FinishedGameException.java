package com.gmail.radekzatec77.tictactoe.exceptions;

/**
 * Vyjimka, která se vystřeluje pokud je hra již dokončena a snažíme se udělat tah
 */
public class FinishedGameException extends Exception {
  public FinishedGameException() {
    super("Tato hra je dokončena");
  }
}
