package com.gmail.radekzatec77.collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

  Matrix<Integer> matrix;

  @Before
  public void setUp() throws Exception {
    matrix = new Matrix<Integer>(5, 6);

    matrix.setRow(5, new Integer[] {26, 27, 28, 29, 30});
    matrix.setRow(4, new Integer[] {21, 22, 23, 24, 25});
    matrix.setRow(3, new Integer[] {16, 17, 18, 19, 20});
    matrix.setRow(2, new Integer[] {11, 12, 13, 14, 15});
    matrix.setRow(1, new Integer[] { 6,  7,  8,  9, 10});
    matrix.setRow(0, new Integer[] { 1,  2,  3,  4,  5});
  }

  @Test
  public void itReturnsRow() throws Exception {
    assertArrayEquals(new Integer[] { 6,  7,  8,  9, 10}, matrix.row(1).toArray());
    assertArrayEquals(new Integer[] {21, 22, 23, 24, 25}, matrix.row(4).toArray());
  }

  @Test
  public void itReturnsColumn() throws Exception {
    assertArrayEquals(new Integer[] { 1,  6, 11, 16, 21, 26}, matrix.col(0).toArray());
    assertArrayEquals(new Integer[] { 4,  9, 14, 19, 24, 29}, matrix.col(3).toArray());
  }

  @Test
  public void itReturnsDiagonals() throws Exception {

    assertArrayEquals(new Integer[] { 1,  7, 13, 19, 25}, matrix.rightDiagonal(1, 1).toArray());
    assertArrayEquals(new Integer[] { 3,  7, 11}, matrix.leftDiagonal(1, 1).toArray());

    assertArrayEquals(new Integer[] { 1 }, matrix.leftDiagonal(0, 0).toArray());

    assertArrayEquals(new Integer[] {16, 22, 28}, matrix.diagonal(1, 4, true).toArray());
    assertArrayEquals(new Integer[] {10, 14, 18, 22, 26}, matrix.diagonal(1, 4, false).toArray());

    assertArrayEquals(new Integer[] {10, 14, 18, 22, 26}, matrix.diagonal(0, 5, false).toArray());

    assertArrayEquals(new Integer[] {6, 12, 18, 24, 30}, matrix.diagonal(4, 5, true).toArray());
    assertArrayEquals(new Integer[] {30}, matrix.diagonal(4, 5, false).toArray());
  }
}