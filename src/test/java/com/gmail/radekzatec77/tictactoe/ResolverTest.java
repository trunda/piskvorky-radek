package com.gmail.radekzatec77.tictactoe;

import com.gmail.radekzatec77.collections.Matrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResolverTest {

  private Stone s(Character c) {
    return new Stone(0, 0, c.equals('B') ? Stone.Color.BLACK : Stone.Color.WHITE);
  }
  
  @Test
  public void resolve() throws Exception {
    Matrix<Stone> board = new Matrix<Stone>(5, 6);
    board.setRow(5, new Stone[] {null, null, null, null, null});
    board.setRow(4, new Stone[] {null, null, null, null, null});
    board.setRow(3, new Stone[] {null, null, null, null, null});
    board.setRow(2, new Stone[] {null, null, null, null, null});
    board.setRow(1, new Stone[] { s('B'),  s('B'),  s('B'),  s('B'), null});
    board.setRow(0, new Stone[] {null, null, null, null, null});

    assertSame(Stone.Color.BLACK, new Resolver(4).resolve(board, 1, 1));


    board = new Matrix<Stone>(5, 6);
    board.setRow(5, new Stone[] {null, null, null, null,  s('B')});
    board.setRow(4, new Stone[] {null, null, null,  s('B'), null});
    board.setRow(3, new Stone[] {null, null,  s('B'), null, null});
    board.setRow(2, new Stone[] {null,  s('B'), null, null, null});
    board.setRow(1, new Stone[] {null, null, null, null, null});
    board.setRow(0, new Stone[] {null, null, null, null, null});

    assertSame(Stone.Color.BLACK, new Resolver(4).resolve(board, 1, 2));


    board = new Matrix<Stone>(5, 6);
    board.setRow(5, new Stone[] { s('B'), null, null, null, null});
    board.setRow(4, new Stone[] { s('B'), null, null, null, null});
    board.setRow(3, new Stone[] { s('B'), null, null, null, null});
    board.setRow(2, new Stone[] { s('B'), null, null, null, null});
    board.setRow(1, new Stone[] {null, null, null, null, null});
    board.setRow(0, new Stone[] {null, null, null, null, null});

    assertSame(Stone.Color.BLACK, new Resolver(4).resolve(board, 0, 5));


    board = new Matrix<Stone>(5, 6);
    board.setRow(5, new Stone[] { s('B'), null, null, null, null});
    board.setRow(4, new Stone[] {null,  s('B'), null, null, null});
    board.setRow(3, new Stone[] {null, null,  s('B'), null, null});
    board.setRow(2, new Stone[] {null, null, null,  s('B'), null});
    board.setRow(1, new Stone[] {null, null, null, null, null});
    board.setRow(0, new Stone[] {null, null, null, null, null});

    assertSame(Stone.Color.BLACK, new Resolver(4).resolve(board, 0, 5));

    board = new Matrix<Stone>(5, 6);
    board.setRow(5, new Stone[] {null, null, null, null, null});
    board.setRow(4, new Stone[] {null,  s('B'), null,  s('B'),  s('B')});
    board.setRow(3, new Stone[] { s('B'),  s('B'),  s('B'), null, null});
    board.setRow(2, new Stone[] {null,  s('B'), null, null, null});
    board.setRow(1, new Stone[] {null, null, null, null, null});
    board.setRow(0, new Stone[] {null, null, null, null, null});

    assertSame(null, new Resolver(4).resolve(board, 1, 1));
  }

}