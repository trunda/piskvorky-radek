package com.gmail.radekzatec77.tictactoe.dao;

import com.gmail.radekzatec77.tictactoe.StoneColor;

import java.util.*;

/**
 * Statistiky hry
 */
public class GameStats {

  private final Map<StoneColor, PlayerStats> stats = new HashMap<>();

  public GameStats() {
    reset();
  }

  public void incWins(StoneColor player) {
    stats.get(player).incWins();
  }

  public void incDraws() {
    for (StoneColor color : StoneColor.class.getEnumConstants()) {
      stats.get(color).incDraws();
    }
  }

  public void incTurns(StoneColor player) {
    stats.get(player).incTurns();
  }

  public int getWins(StoneColor color) {
    return stats.get(color).getWins();
  }

  public int getDraws(StoneColor color) {
    return stats.get(color).getDraws();
  }

  public int getTurns(StoneColor color) {
    return stats.get(color).getDraws();
  }

  public int getSeconds(StoneColor color) {
    return stats.get(color).getSeconds();
  }

  public void incSeconds(StoneColor color, int seconds) {
    stats.get(color).incSeconds(seconds);
  }

  public Collection<PlayerStats> getStats() {
    return stats.values();
  }

  public void newMatch() {
    for (PlayerStats stats: stats.values()) {
      stats.newMatch();
    }
  }

  public PlayerStats getStatsFor(StoneColor color) {
    return stats.get(color);
  }

  public void reset() {
    stats.clear();
    for (StoneColor color : StoneColor.class.getEnumConstants()) {
      stats.put(color, new PlayerStats(color));
    }
  }
}
