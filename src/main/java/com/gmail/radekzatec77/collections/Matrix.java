package com.gmail.radekzatec77.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by trunda on 10.05.17.
 */
public class Matrix<T extends Object> {
  private final int width;
  private final int height;
  private final Object[][] data;

  public Matrix(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new Object[width][height];
  }

  public T get(int x, int y) {
    return (T) data[x][y];
  }

  public void setRow(int y, T[] row) throws MatrixException {
    if (row.length != width) {
      throw new MatrixException("Row length has to be same as matrix width");
    }
    int x = 0;
    for (T cell : row) {
      this.set(x++, y, cell);
    }
  }

  public void set(int x, int y, T element)
  {
    this.data[x][y] = element;
  }

  public List<T> row(int y)
  {
    List<T> result = new ArrayList<T>(height);
    for (int x = 0; x < width; x++) {
      result.add(this.get(x, y));
    }
    return result;
  }

  public List<T> col(int x) {
    return (List<T>) Arrays.asList(data[x]);
  }

  public List<T> diagonal(int x, int y, boolean right) {
    List<T> result = new ArrayList<T>();

    int startX = x;
    int startY = y;

    // Find where to start
    // 1. we are going down by one
    // 2. we are going left (resp right) by one
    // 3. when we are on the edge, this is start of diagonal
    while ((right && startX > 0 && startY > 0) || (!right && startX < width - 1 && startY > 0)) {
      startY += -1;
      startX += right ? -1 : 1;
    }

    // Get all items on diagonal
    // 1. we are going row by row from start
    // 2. we are going one right (resp left) and getting the cell
    // 3. when we are on the matrix edge, we have all diagonal
    int iX = startX;
    for (int iY = startY; iY < height; iY++) {
      result.add(get(iX, iY));
      iX += right ? 1 : -1;
      if (iX < 0 || iX >= width) {
        break;
      }
    }

    return result;
  }


  public List<T> rightDiagonal(int x, int y) {
    return diagonal(x, y, true);
  }

  public List<T> leftDiagonal(int x, int y) {
    return diagonal(x, y, false);
  }

  public List[] diagonals(int x, int y) {
    return new List[] { leftDiagonal(x, y), rightDiagonal(x, y) };
  }
}
