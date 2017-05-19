package com.gmail.radekzatec77.tictactoe;

import com.gmail.radekzatec77.tictactoe.dao.GameStats;
import com.gmail.radekzatec77.tictactoe.dao.PlayerStats;
import com.gmail.radekzatec77.tictactoe.exceptions.FinishedMatchException;
import com.gmail.radekzatec77.tictactoe.exceptions.FullColumnException;
import com.gmail.radekzatec77.tictactoe.exceptions.FinishedGameException;

/**
 * Počítá statistiky a vyhodnocuje hru
 */
public class Game {
  public final int matches;
  public final Match match;
  public final GameStats gameStats = new GameStats();


  public Game(Match match, int matches) {
    this.matches = matches;
    this.match = match;
  }

  /**
   * Provede tah ve hře a navýší statistiky
   * @param column
   * @throws FullColumnException
   * @throws FinishedGameException
   * @throws FinishedMatchException
   */
  public void turn(int column) throws FullColumnException, FinishedGameException, FinishedMatchException {
    if (isFinished()) { throw new FinishedGameException(); }

    StoneColor onTurn = match.getPlayerOnTurn();
    match.turn(column);
    gameStats.incTurns(onTurn);

    if (match.isFinished()) {
      if (match.isDraw()) {
        gameStats.incDraws();
      }

      if (match.isWinner()) {
        gameStats.incWins(match.getWinner());
      }
    }
  }

  /**
   * Vrací celkového vítěze hry nebo null
   * @return
   */
  public StoneColor getWinner() {
    for (PlayerStats stats : gameStats.getStats()) {
      if (stats.getWins() == matches) {
        return stats.player;
      }
    }

    return null;
  }

  /**
   * Posouvá hru na další zápas
   * @throws FinishedGameException
   */
  public void nextMatch() throws FinishedGameException {
    if (isFinished()) { throw new FinishedGameException(); }
    gameStats.newMatch();
    match.reset();
  }

  /**
   * Resetuje celou hru
   */
  public void reset() {
    match.reset();
    gameStats.reset();
  }

  /**
   * Je hra ukončena?
   * @return
   */
  public boolean isFinished() {
    return getWinner() != null;
  }
}
