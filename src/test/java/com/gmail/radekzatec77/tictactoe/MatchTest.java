package com.gmail.radekzatec77.tictactoe;

import com.gmail.radekzatec77.tictactoe.dao.Between;
import com.gmail.radekzatec77.tictactoe.exceptions.FinishedMatchException;
import com.gmail.radekzatec77.tictactoe.exceptions.FullColumnException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchTest {

  Match match;

  @Before
  public void setUp() throws Exception {
    match = new Match(new Board(3, 3), 3);
  }

  @Test
  public void itCyclesPlayersAfterTurns() throws Exception {
    StoneColor first = match.getPlayerOnTurn();
    match.turn(0);
    assertNotEquals(first, match.getPlayerOnTurn());
    match.turn(0);
    assertEquals(first, match.getPlayerOnTurn());
  }

  @Test(expected = FullColumnException.class)
  public void itFiresExceptionOnFullColumn() throws Exception {
    for (int i = 0; i < 4; i++) {
      match.turn(0);
    }
  }

  @Test
  public void itFindsWinner() throws Exception {
    StoneColor winner = match.getPlayerOnTurn();
    /**
     * +-+-+-+
     * | | |x|
     * +-+-+-+
     * |o|x|o|
     * +-+-+-+
     * |x|o|x|
     * +-+-+-+
     */
    for (Integer column : new Integer[] {0, 1, 2, 0, 1, 2, 2}) {
      match.turn(column);
    }

    assertTrue(match.isFinished());
    assertTrue(match.isWinner());
    assertNotNull(match.getWinner());
    assertEquals(match.getWinner(), winner);
    assertEquals(new Between(0, 0, 2, 2), match.getWinnerBetween());
  }

  @Test(expected = FinishedMatchException.class)
  public void itWillNotAllowNextTurnAfterWin() throws Exception {
    for (Integer column : new Integer[] {0, 1, 2, 0, 1, 2, 2, 0}) {
      match.turn(column);
    }
  }

  @Test
  public void itRecognizeDraw() throws Exception {
    StoneColor winner = match.getPlayerOnTurn();
    /**
     * +-+-+-+
     * |x|o|x|
     * +-+-+-+
     * |o|o|x|
     * +-+-+-+
     * |x|x|o|
     * +-+-+-+
     */
    for (Integer column : new Integer[] {0, 0, 1, 2, 2, 1, 0, 1, 2}) {
      match.turn(column);
    }

    assertTrue(match.isFinished());
    assertTrue(match.isDraw());
    assertFalse(match.isWinner());
    assertNull(match.getWinner());
  }

  @Test
  public void looserIsOnTurnNextTime() throws Exception {
    StoneColor winner = match.getPlayerOnTurn();
    for (Integer column : new Integer[] {0, 1, 2, 0, 1, 2, 2}) {
      match.turn(column);
    }
    match.reset();
    assertNotEquals(winner, match.getPlayerOnTurn());
  }
}