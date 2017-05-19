package com.gmail.radekzatec77.tictactoe;

import com.gmail.radekzatec77.tictactoe.dao.Between;
import com.gmail.radekzatec77.tictactoe.exceptions.FinishedMatchException;
import com.gmail.radekzatec77.tictactoe.exceptions.FullColumnException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Zápas - stará se o tahy a vyhodnocení zápasu
 */
public class Match {
  private final List<StoneColor> players;
  private final int howManyInRow;
  private int onTurnIndex = 0;

  public final Board board;

  public Match(Board board, int howManyInRow) {
    this.players = Arrays.asList(StoneColor.class.getEnumConstants());
    this.howManyInRow = howManyInRow;
    this.board = board;
    setRadomPlayerOnTurn();
  }

  public Board getBoard() {
    return board;
  }

  /**
   * Vyresetuje hru
   */
  public void reset() {
    board.reset();
    nextPlayer();
  }

  public void turn(int column) throws FinishedMatchException, FullColumnException {
    if (isFinished()) {
      throw new FinishedMatchException();
    }
    board.put(column, getPlayerOnTurn());

    if (!isFinished()) {
      nextPlayer();
    }
  }

  /**
   * Vrací vízěze, nebo null
   *
   * Null znamená, že ještě není dohráno, nebo je remíza
   *
   * @return
   */
  public StoneColor getWinner() {
    if (board.sameNextEachOther(howManyInRow) != null) {
      return getPlayerOnTurn();
    }

    return null;
  }

  /**
   * Která políčka vyhrála? (první a poslední)
   * @return
   */
  public Between getWinnerBetween() {
    if (isWinner()) {
      return board.sameNextEachOther(howManyInRow);
    }
    return null;
  }

  /**
   * Je hra dokončena (to je remíza nebo vízěz)
   * @return
   */
  public boolean isFinished() {
    return getWinner() != null || board.isFull();
  }

  /**
   * Je to remíza?
   * @return
   */
  public boolean isDraw() {
    return getWinner() == null && board.isFull();
  }

  /**
   * Máme vízěze?
   * @return
   */
  public boolean isWinner() {
    return getWinner() != null;
  }

  /**
   * Hráč, který je na tahu
   * @return
   */
  public StoneColor getPlayerOnTurn() {
    return players.get(onTurnIndex);
  }

  /**
   * Nastaví náhodného hráče na tah
   */
  private void setRadomPlayerOnTurn() {
    onTurnIndex = new Random().nextInt(players.size());
  }

  /**
   * Nastaví náhodného hráče na tah
   */
  private void nextPlayer() {
    onTurnIndex = (onTurnIndex + 1) % players.size();
  }
}
