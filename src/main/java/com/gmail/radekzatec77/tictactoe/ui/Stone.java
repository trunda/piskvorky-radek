package com.gmail.radekzatec77.tictactoe.ui;

import com.gmail.radekzatec77.tictactoe.StoneColor;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

/**
 * GUI samotného kamene
 */
public class Stone {
  /**
   * Odsazení mezi kameny
   */
  public static final int STONE_PADDING = 5;
  /**
   * Velikost kamene
   */
  public static final int STONE_SIZE = 55;
  /**
   * Barva zvýraznění kamene (po najetí myší - první volný v daném sloupci)
   */
  public static final Color HOVERED_COLOR = Color.decode("#919191");
  /**
   * Mapa barev kamenů
   */
  public static final Map<StoneColor, Color> COLOR_MAP;
  static {
    COLOR_MAP = new HashMap<>();
    COLOR_MAP.put(StoneColor.BLACK, Color.decode("#c32328"));
    COLOR_MAP.put(StoneColor.WHITE, Color.decode("#ffec1b"));
    COLOR_MAP.put(null, Color.decode("#ffffff"));
  }

  /**
   * Sloupec kamene
   */
  public final int column;
  /**
   * Řádek kamene
   */
  public final int row;
  /**
   * Celková výška desky - tu potřebuejem pro přepočet umístění kamenů od levého spodního rohu
   */
  public final int height;
  /**
   * Barva kamene
   */
  private StoneColor color;
  /**
   * Značí, jestli je kámen zvýrazněn
   */
  private boolean isHovered = false;

  public Stone(int column, int row, int height) {
    this.column = column;
    this.height = height;
    this.row = row;
  }

  /**
   * Nastavuje barvu kamene, volá se po změně herní desky
   * @param color
   */
  public void setColor(StoneColor color) {
    this.color = color;
  }

  /**
   * Nastavuje příznak zvýraznění
   * @param hovered
   */
  public void setHovered(boolean hovered) {
    this.isHovered = hovered;
  }

  /**
   * Vykreslení kamene
   * @param g
   */
  public void draw(Graphics2D g) {
    Ellipse2D.Double circle = new Ellipse2D.Double(getX(), getY(), STONE_SIZE, STONE_SIZE);
    g.setStroke(new BasicStroke(3));
    g.setColor(COLOR_MAP.get(color));

    if (isHovered) {
      g.setColor(HOVERED_COLOR);
    }

    g.fill(circle);

    g.setColor(Color.BLACK);
    g.draw(circle);
  }

  /**
   * Celková velikost kamene
   * @return
   */
  public static int size() {
    return Stone.STONE_SIZE + 2 * Stone.STONE_PADDING;
  }

  /**
   * X umístění kamene
   * @return
   */
  private int getX() {
    return STONE_PADDING + (column * (STONE_SIZE + 2 * STONE_PADDING));
  }

  /**
   * Y umístění kamene
   * @return
   */
  private int getY() {
    return STONE_PADDING + ((height - 1 - row) * (STONE_SIZE + 2 * STONE_PADDING));
  }

  /**
   * Střed kamene
   * @return
   */
  public Point getMiddle() {
    return new Point(getX() - STONE_PADDING + (size() / 2), getY() - STONE_PADDING + (size() / 2));
  }
}
