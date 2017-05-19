package com.gmail.radekzatec77.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Pomocná statická třída pro gui
 */
public class Utils {
  /**
   * Přidá horizontální separátor
   *
   * @param component
   */
  public static void addHorizontalSeparator(Container component) {
    component.add(Box.createRigidArea(new Dimension(0, 20)));
    component.add(getHorizontalSeparator());
    component.add(Box.createRigidArea(new Dimension(0, 20)));
  }

  /**
   * Vrací horizontální separátor
   * @return
   */
  public static JSeparator getHorizontalSeparator() {
    JSeparator separator;
    separator = new JSeparator(JSeparator.HORIZONTAL);
    separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
    return separator;
  }

}
