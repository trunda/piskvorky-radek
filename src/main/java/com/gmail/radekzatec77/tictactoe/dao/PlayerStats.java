package com.gmail.radekzatec77.tictactoe.dao;

import com.gmail.radekzatec77.tictactoe.StoneColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Statistiky hráče
 */
public class PlayerStats {
  public final StoneColor player;
  private final List<MatchStats> matchStats = new ArrayList<>();

  public PlayerStats(StoneColor player) {
    this.player = player;
    this.matchStats.add(new MatchStats());
  }

  public int getWins() {
    int result = 0;
    for (MatchStats stats : matchStats) {
      result += stats.getWins();
    }

    return result;
  }

  public int getLosts() {
    return getMathes() - getWins() - getDraws();
  }

  public int getDraws() {
    int result = 0;
    for (MatchStats stats : matchStats) {
      result += stats.getDraws();
    }

    return result;
  }

  public int getTurns() {
    int result = 0;
    for (MatchStats stats : matchStats) {
      result += stats.getTurns();
    }

    return result;
  }

  public int getSeconds() {
    int result = 0;
    for (MatchStats stats : matchStats) {
      result += stats.getSeconds();
    }

    return result;
  }

  public void incWins() {
    getLastMatchStats().incWins();
  }

  public void incDraws() {
    getLastMatchStats().incDraws();
  }

  public void incTurns() {
    getLastMatchStats().incTurns();
  }

  public void incSeconds(int seconds) {
    getLastMatchStats().incSeconds(seconds);
  }

  public int getMathes() {
    return matchStats.size();
  }

  public MatchStats getLastMatchStats() {
    return matchStats.get(matchStats.size() - 1);
  }

  public void newMatch() {
    matchStats.add(new MatchStats());
  }
}
