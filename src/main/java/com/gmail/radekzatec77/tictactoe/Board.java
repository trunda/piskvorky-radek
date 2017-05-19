package com.gmail.radekzatec77.tictactoe;

import com.gmail.radekzatec77.tictactoe.dao.*;
import com.gmail.radekzatec77.tictactoe.exceptions.FullColumnException;

import java.util.ArrayList;
import java.util.List;

/**
 * Herní matice
 */
public class Board {

  /**
   * Šířka matice
   */
  public final int width;
  /**
   * Výška matice
   */
  public final int height;
  /**
   * Data (barvy/kameny) matice
   */
  private StoneColor[] stones;

  /**
   * Konstruktor
   *
   * @param width  Šířka matice
   * @param height Výška matice
   */
  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    reset();
  }

  /**
   * Konstruktor s předdefinovaným obsahem matice
   *
   * @param width  Šířka matice
   * @param height Výška matice
   * @param stones   Umístění kamenů
   */
  public Board(int width, int height, StoneColor[] stones) {
    this(width, height);
    if (stones.length != width * height) {
      throw new IllegalArgumentException("Velikost dat neodpovídá rozměrům herní matice");
    }
    this.stones = stones;
  }

  /**
   * Vyprázdní matici
   */
  public void reset() {
    stones = new StoneColor[width * height];
  }

  /**
   * Vrací barvu na pozici
   *
   * @param coordinate
   * @return
   */
  public StoneColor get(Coordinate coordinate) {
    return stones[ordinal(coordinate)];
  }

  /**
   * Vrací barvu na pozici
   *
   * @param column Sloupec
   * @param row    Řadek
   * @return
   */
  public StoneColor get(int column, int row) {
    return get(new Coordinate(column, row));
  }

  /**
   * Vhodí barvu (kámen) na daný sloupec
   *
   * @param column
   * @param stone
   * @throws FullColumnException Pokud není žádný volný řádek
   */
  public void put(int column, StoneColor stone) throws FullColumnException {
    set(new Coordinate(column, getEmptyRow(column)), stone);
  }

  /**
   * Vrací pole indexů volných sloupců
   * @return
   */
  public Integer[] getFreeColumns() {
    List<Integer> result = new ArrayList<>();
    for (int column = 0; column < width; column++) {
      if (this.isColumnFree(column)) {
        result.add(column);
      }
    }

    return result.toArray(new Integer[result.size()]);
  }

  /**
   * Testuje, jestli je herní pole plné
   * @return
   */
  public boolean isFull() {
    return getFreeColumns().length == 0;
  }

  /**
   * Vrací barvy na řádku
   *
   * @param row
   * @return
   */
  public Cells getRow(int row) {
    Cell[] result = new Cell[width];
    for (int column = 0; column < width; column++) {
      result[column] = new Cell(column, row, get(column, row));
    }

    return new Cells(result);
  }

  /**
   * Vrací barvy ve sloupci
   *
   * @param column
   * @return
   */
  public Cells getColumn(int column) {
    Cell[] result = new Cell[height];
    for (int row = 0; row < height; row++) {
      result[row] = new Cell(column, row, get(column, row));
    }

    return new Cells(result);
  }

  /**
   * Otestuje jestli je daný sloupce volný pro vložení kabelu
   * @param column
   * @return
   */
  public boolean isColumnFree(int column) {
    try {
      this.getEmptyRow(column);
      return true;
    } catch (FullColumnException ex) {
      return false;
    }
  }

  /**
   * Vrátí diagonály, které procházejí danýnm prvkem
   * - výstupem je pole, které má 2 prvky.
   * - každý prvek je pole obsahující prvky pole
   * - prvky, které patří do diagonály s prvkem A, splňují podmínku: abs(A.x - B.x) == abs(A.y - B.y)
   * - jinými slovy, prvky v diagonále jsou ty, které mají stejnou vzdálenost X a Y souřadnic
   *
   * @param coordinate
   * @return
   */
  public Diagonals getDiagonals(Coordinate coordinate) {
    List<Cell> left = new ArrayList<>();
    List<Cell> right = new ArrayList<>();

    for (int column = 0; column < width; column++) {
      for (int row = 0; row < height; row++) {
        int colDistance = coordinate.column - column;
        int rowDistance = coordinate.row - row;
        int distance = colDistance * rowDistance;
        if (Math.abs(colDistance) == Math.abs(rowDistance)) {
          if (distance <= 0) {
            left.add(new Cell(column, row, get(column, row)));
          }
          if (distance >= 0) {
            right.add(new Cell(column, row, get(column, row)));
          }
        }
      }
    }

    return new Diagonals(
            new Cells(left.toArray(new Cell[left.size()])),
            new Cells(right.toArray(new Cell[right.size()]))
    );
  }

  /**
   * Vrací 2 body v matici mezi kterými je počet stejných prvků veťší
   * nebo roven požadovanému počtu
   *
   * @param howMany
   * @return Pole dvou souřadnic nebo null, pokud žádné takové souřadnice neexistují
   */
  public Between sameNextEachOther(int howMany) {
    for (int column = 0; column < width; column++) {
      for (int row = 0; row < height; row++) {
        Between result = sameNextEachOther(new Coordinate(column, row), howMany);
        if (result != null) {
          return result;
        }
      }
    }
    return null;
  }

  /**
   * Vrací 2 body v matici mezi kterými je počet stejných prvků veťší
   * nebo roven požadovanému počtu
   *
   * Konrolu provádí z pohledu konkrétního bodu
   *
   * @param howMany
   * @return Pole dvou souřadnic nebo null, pokud žádné takové souřadnice neexistují
   */
  private Between sameNextEachOther(Coordinate coordinate, int howMany) {

    List<Cells> checks = new ArrayList<>();
    Diagonals diagonals = getDiagonals(coordinate);
    checks.add(getColumn(coordinate.column));
    checks.add(getRow(coordinate.row));
    checks.add(diagonals.left);
    checks.add(diagonals.right);

    for (Cells cells : checks) {
      Between between = cells.sameInRow(howMany);
      if (between != null) {
        return between;
      }
    }

    return null;
  }


  /**
   * Vrací číslo prvního řádku, který je v daném sloupci volný
   *
   * @param column
   * @return Index řádku
   * @throws FullColumnException Pokud není žádný volný řádek
   */
  public int getEmptyRow(int column) throws FullColumnException {
    for (int row = 0; row < height; row++) {
      if (get(new Coordinate(column, row)) == null) {
        return row;
      }
    }

    throw new FullColumnException(column);
  }

  /**
   * Převede pozici na ordinální hodnotu (mapovací funkce)
   *
   * @param coordinate
   * @return
   */
  private int ordinal(Coordinate coordinate) {
    return coordinate.column+ ((height - 1 - coordinate.row) * width);
  }

  /**
   * Nastaví barvu na pozici
   *
   * @param coordinate
   * @param stone
   */
  private void set(Coordinate coordinate, StoneColor stone) {
    stones[ordinal(coordinate)] = stone;
  }
}
