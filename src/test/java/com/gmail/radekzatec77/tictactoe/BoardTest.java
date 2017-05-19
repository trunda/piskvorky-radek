package com.gmail.radekzatec77.tictactoe;

import com.gmail.radekzatec77.tictactoe.dao.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

  private static Character[] cellsToValues(Cells cells) {
    Character[] result = new Character[cells.cells.length];
    int i = 0;
    for (Cell cell : cells.cells) {
      if (cell.stone == null) {
        result[i++] = null;
      } else if (cell.stone.equals(StoneColor.WHITE)) {
        result[i++] = 'W';
      } else {
        result[i++] = 'B';
      }
    }

    return result;
  }

  private static Board createBoard(Character[][] colors) {
    int height = colors.length;
    int width = colors[0].length;
    StoneColor[] data = new StoneColor[height * width];
    for (int column = 0; column < width; column++) {
      for (int row = 0; row < height; row++) {
        StoneColor stone = null;
        Character ch = colors[row][column];
        if (ch.equals('B')) {
          stone = StoneColor.BLACK;
        }
        if (ch.equals('W')) {
          stone = StoneColor.WHITE;
        }
        data[column + (width * row)] = stone;
      }
    }
    return new Board(width, height, data);
  }

  @Test
  public void itReturnsRow() throws Exception {

    Board b = createBoard(new Character[][] {
            {'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'B', 'B', 'W', 'W', 'B', 'B', 'W'},
            {'W', 'W', 'W', 'B', 'B', 'B', 'B'},
            {'W', 'W', 'B', 'B', 'W', 'W', 'B'},
            {'W', 'W', 'B', 'B', 'W', 'B', 'W'},
            {'B', 'B', 'W', 'B', 'B', 'W', 'B'}
    });

    assertArrayEquals(new Character[] {'B', 'B', 'W', 'B', 'B', 'W', 'B'}, cellsToValues(b.getRow(0)));
    assertArrayEquals(new Character[] {'W', 'W', 'B', 'B', 'W', 'W', 'B'}, cellsToValues(b.getRow(2)));
  }

  @Test
  public void itReturnsColumn() throws Exception {
    Board b = createBoard(new Character[][] {
            {'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'B', 'B', 'W', 'W', 'B', 'B', 'W'},
            {'W', 'W', 'W', 'B', 'B', 'B', 'B'},
            {'W', 'W', 'B', 'B', 'W', 'W', 'B'},
            {'W', 'W', 'B', 'B', 'W', 'B', 'W'},
            {'B', 'B', 'W', 'B', 'B', 'W', 'B'}
    });

    assertArrayEquals(new Character[] {'B', 'W', 'W', 'W', 'B', 'W'}, cellsToValues(b.getColumn(0)));
    assertArrayEquals(new Character[] {'W', 'B', 'B', 'W', 'W', 'W',}, cellsToValues(b.getColumn(2)));
  }

  @Test
  public void itReturnsDiagonals() throws Exception {
    Board b = createBoard(new Character[][] {
            {'W', 'B', 'W'},
            {'B', 'B', 'W'},
            {'W', 'W', 'B'},
    });

    Diagonals diagonals;

    diagonals = b.getDiagonals(new Coordinate(1, 1));
    assertArrayEquals(new Character[] {'W', 'B', 'W'}, cellsToValues(diagonals.right));
    assertArrayEquals(new Character[] {'W', 'B', 'B'}, cellsToValues(diagonals.left));

    diagonals = b.getDiagonals(new Coordinate(0, 0));
    assertArrayEquals(new Character[] {'W', 'B', 'W'}, cellsToValues(diagonals.right));
    assertArrayEquals(new Character[] {'W'}, cellsToValues(diagonals.left));

    diagonals = b.getDiagonals(new Coordinate(2, 0));
    assertArrayEquals(new Character[] {'B'}, cellsToValues(diagonals.right));
    assertArrayEquals(new Character[] {'W', 'B', 'B'}, cellsToValues(diagonals.left));

    diagonals = b.getDiagonals(new Coordinate(2, 2));
    assertArrayEquals(new Character[] {'W', 'B', 'W'}, cellsToValues(diagonals.right));
    assertArrayEquals(new Character[] {'W'}, cellsToValues(diagonals.left));

    diagonals = b.getDiagonals(new Coordinate(0, 2));
    assertArrayEquals(new Character[] {'W'}, cellsToValues(diagonals.right));
    assertArrayEquals(new Character[] {'W', 'B', 'B'}, cellsToValues(diagonals.left));
  }

  @Test
  public void itFindsSameNextEachOther() throws Exception {
    Board b;
    b = createBoard(new Character[][] {
            {' ', ' ', ' ', ' '},
            {' ', 'B', 'B', 'W'},
            {' ', 'W', 'B', ' '},
            {' ', ' ', 'W', ' '},
    });
    assertNull(b.sameNextEachOther(3));

    // Řádek na tři políčka
    b = createBoard(new Character[][] {
            {' ', ' ', ' ', ' '},
            {'B', 'B', 'B', ' '},
            {' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' '},
    });
    assertEquals(new Between(0, 2, 2, 2), b.sameNextEachOther(3));

    // Sloupec na tři políčka
    b = createBoard(new Character[][] {
            {' ', ' ', ' ', ' '},
            {' ', ' ', 'W', ' '},
            {' ', ' ', 'W', ' '},
            {' ', ' ', 'W', ' '},
    });
    assertEquals(new Between(2, 0, 2, 2), b.sameNextEachOther(3));

    // Diagonála doprava
    b = createBoard(new Character[][] {
            {' ', ' ', ' ', 'W'},
            {' ', ' ', 'W', ' '},
            {' ', 'W', ' ', ' '},
            {' ', ' ', ' ', ' '},
    });
    assertEquals(new Between(1, 1, 3, 3), b.sameNextEachOther(3));

    // Diagonála doleva
    b = createBoard(new Character[][] {
            {'W', ' ', ' ', ' '},
            {' ', 'W', ' ', ' '},
            {' ', ' ', 'W', ' '},
            {' ', ' ', ' ', ' '},
    });
    assertEquals(new Between(0, 3, 2, 1), b.sameNextEachOther(3));
  }

  @Test
  public void itReturnsFreeColumns() throws Exception {
    Board b = createBoard(new Character[][] {
            {'W', ' ', 'W', ' '},
            {'W', 'W', 'W', ' '},
            {'W', 'W', 'W', ' '},
            {'W', 'W', 'W', 'W'},
    });

    assertArrayEquals(new Integer[] {1, 3}, b.getFreeColumns());
  }

  @Test
  public void itRightTestsFullBoard() throws Exception {
    Board b;
    b = createBoard(new Character[][] {
            {'W', ' ', 'W', ' '},
            {'W', 'W', 'W', ' '},
            {'W', 'W', 'W', ' '},
            {'W', 'W', 'W', 'W'},
    });
    assertFalse(b.isFull());

    b = createBoard(new Character[][] {
            {'W', 'W', 'W', 'W'},
            {'W', 'W', 'W', 'W'},
            {'W', 'W', 'W', 'W'},
            {'W', 'W', 'W', 'W'},
    });
    assertTrue(b.isFull());
  }
}