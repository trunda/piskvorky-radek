package com.gmail.radekzatec77.tictactoe.ui;

import com.gmail.radekzatec77.tictactoe.Match;
import com.gmail.radekzatec77.tictactoe.dao.Between;
import com.gmail.radekzatec77.tictactoe.exceptions.FullColumnException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.*;

public class BoardComponent extends JPanel {

  public static final Color BACKGROUND = Color.decode("#009ee0");

  public final Match match;

  private final ArrayList<Stone> stones = new ArrayList<>();
  private Integer selectedColumn = null;
  private final java.util.List<ColumnSelectionListener> listeners = new ArrayList<>();
  private Between lineBetween = null;


  public BoardComponent(Match match) {
    this.match = match;
    this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    this.bindListeners();
    this.setDoubleBuffered(true);
    this.initStones();
  }

  public void addListener(ColumnSelectionListener listener) {
    listeners.add(listener);
  }

  /**
   * Vrátí UI kámen na daných souřadnicích
   * @param column
   * @param row
   * @return
   */
  private Stone getStone(int column, int row) {
    for (Stone stone : stones) {
      if (stone.row == row && stone.column == column) {
        return stone;
      }
    }

    return null;
  }

  public void drawLineBetween(Between between) {
    this.lineBetween = between;
    repaint();
  }

  /**
   * Vykreslení herní desky
   * @param g
   */
  @Override
  public void paint(Graphics g) {
    setStoneColor();
    setHovered(selectedColumn);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(BACKGROUND);
    g2.fillRect(0, 0, getWidth(), getHeight());
    drawLineBetween(g2);
    for (Stone stone : stones) {
      stone.draw(g2);
    }
  }

  /**
   * Nakreslí čáru mezi dvěma body
   * @param g2
   */
  private void drawLineBetween(Graphics2D g2) {
    if (lineBetween != null) {
      Stone from = getStone(lineBetween.from.column, lineBetween.from.row);
      Stone to = getStone(lineBetween.to.column, lineBetween.to.row);
      g2.setColor(Color.BLACK);
      g2.setStroke(new BasicStroke(8));
      g2.draw(new Line2D.Float(from.getMiddle(), to.getMiddle()));
    }
  }

  /**
   * Rozměry herní desky
   * @return
   */
  public Dimension getDimension() {
    return new Dimension(match.board.width * Stone.size(), (match.board.height * (Stone.size() + 8)));
  }

  /**
   * Inicializuje pole kamenů
   */
  private void initStones() {
    for (int column = 0; column < match.board.width; column++) {
      for (int row = 0; row < match.board.height; row++) {
        stones.add(new Stone(column, row, match.board.height));
      }
    }
  }

  /**
   * Nastaví barvu všem kamenům podle herní desky
   */
  private void setStoneColor() {
    for (int column = 0; column < match.board.width; column++) {
      for (int row = 0; row < match.board.height; row++) {
        getStone(column, row).setColor(match.board.get(column, row));
      }
    }
  }

  /**
   * Nastaví zvýrazněný kámen
   * @param column
   */
  private void setHovered(Integer column) {
    for (Stone stone : stones) {
      stone.setHovered(false);
    }
    if (column != null && match.board.isColumnFree(column)) {
      try {
        int row = match.board.getEmptyRow(column);
        getStone(column, row).setHovered(true);
      } catch (FullColumnException e) {
        // Tohle by se nemělo stát!
      }
    }
    repaint();
  }

  /**
   * Připojí všechny potřebné posluchače
   */
  private void bindListeners() {
    // Kontroluje pohyb myši a výběr sloupce
    this.addMouseMotionListener(new MouseAdapter() {
      public void mouseMoved(MouseEvent e) {
        int column = e.getX() / Stone.size();
        if (selectedColumn == null || selectedColumn != column) {
          selectedColumn = column;
          repaint();
        }
      }
    });

    // Kontroluje opuštění myši a tak výběr sloupce kliknutím
    this.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        if (match.board.isColumnFree(selectedColumn)) {
          for (ColumnSelectionListener listener : listeners) {
            listener.columnSelected(selectedColumn);
          }
        }
      }

      public void mouseExited(MouseEvent e) {
        if (selectedColumn != null) {
          selectedColumn = null;
          repaint();
        }
      }
    });
  }

}
