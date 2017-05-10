package com.gmail.radekzatec77.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

  private Game game;

  @Before
  public void setUp() throws Exception {
    game = new Game(7, 6);
  }

  @Test
  public void getWidth() throws Exception {
    assertEquals(7, game.getWidth());
  }

  @Test
  public void getHeight() throws Exception {
    assertEquals(6, game.getHeight());
  }

  @Test
  public void setStone() throws Exception {
    assertArrayEquals(new int[] {0, 0}, game.setStone(0, Stone.Color.BLACK));
    assertArrayEquals(new int[] {0, 1}, game.setStone(0, Stone.Color.BLACK));
  }

  @Test(expected = GameException.class)
  public void setStoneException() throws Exception {
    game.setStone(0, 0, Stone.Color.BLACK);
    game.setStone(0, 0, Stone.Color.BLACK);
  }

  @Test(expected = GameException.class)
  public void setStoneExceptionWhenOverfilled() throws Exception {
    for (int i = 0; i < 7; i++) {
      game.setStone(0, Stone.Color.BLACK);
    }
  }

  @Test
  public void getStone() throws Exception {
    // When we add stones, they are stacked one on another
    game.setStone(0, Stone.Color.BLACK);
    game.setStone(0, Stone.Color.WHITE);

    assertSame(Stone.Color.BLACK, game.getStone(0, 0).getColor());
    assertSame(Stone.Color.WHITE, game.getStone(0, 1).getColor());

  }

  @Test(expected = GameException.class)
  public void getWinnerException() throws Exception {
    game.setStone(0, Stone.Color.BLACK);
    game.setStone(1, Stone.Color.BLACK);
    game.setStone(2, Stone.Color.BLACK);
    game.setStone(3, Stone.Color.BLACK);

    assertSame(Stone.Color.BLACK, game.getWinner());

    game.setStone(0, Stone.Color.BLACK);
  }

  @Test
  public void getWinner() throws Exception {
    game.setStone(0, Stone.Color.BLACK);
    game.setStone(1, Stone.Color.BLACK);
    game.setStone(2, Stone.Color.BLACK);
    game.setStone(3, Stone.Color.BLACK);

    assertSame(Stone.Color.BLACK, game.getWinner());
    game.reset();

    game.setStone(0, Stone.Color.BLACK);
    game.setStone(0, Stone.Color.BLACK);
    game.setStone(0, Stone.Color.BLACK);
    game.setStone(0, Stone.Color.BLACK);

    assertSame(Stone.Color.BLACK, game.getWinner());
    game.reset();

    game.setStone(0, Stone.Color.BLACK);
    game.setStone(1, Stone.Color.WHITE);
    game.setStone(1, Stone.Color.BLACK);
    game.setStone(2, Stone.Color.WHITE);
    game.setStone(2, Stone.Color.WHITE);
    game.setStone(2, Stone.Color.BLACK);
    game.setStone(3, Stone.Color.WHITE);
    game.setStone(3, Stone.Color.WHITE);
    game.setStone(3, Stone.Color.WHITE);
    game.setStone(3, Stone.Color.BLACK);

    assertSame(Stone.Color.BLACK, game.getWinner());
    game.reset();

    game.setStone(3, Stone.Color.BLACK);
    game.setStone(2, Stone.Color.WHITE);
    game.setStone(2, Stone.Color.BLACK);
    game.setStone(1, Stone.Color.WHITE);
    game.setStone(1, Stone.Color.WHITE);
    game.setStone(1, Stone.Color.BLACK);
    game.setStone(0, Stone.Color.WHITE);
    game.setStone(0, Stone.Color.WHITE);
    game.setStone(0, Stone.Color.WHITE);
    game.setStone(0, Stone.Color.BLACK);

    assertSame(Stone.Color.BLACK, game.getWinner());
    game.reset();

    assertSame(null, game.getWinner());

  }

}