package com.gmail.radekzatec77.tictactoe.exceptions;

/**
 * Vyjimka, která se vystřeluje pokud je zápas již dokončen a snažíme se udělat tah
 */
public class FinishedMatchException extends Exception {
  public FinishedMatchException() {
    super("Tato hra je již dohrána");
  }
}
