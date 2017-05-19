package com.gmail.radekzatec77.tictactoe.ui;

/**
 * Listener pro výběr sloupce, vystřeluje ho komponenta desky
 */
public interface ColumnSelectionListener {
  /**
   * Sloupec byl vybrán
   * @param column Index sloupce, který byl vybrán
   */
  public void columnSelected(int column);
}
