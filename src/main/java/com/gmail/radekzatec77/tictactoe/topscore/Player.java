package com.gmail.radekzatec77.tictactoe.topscore;

import com.gmail.radekzatec77.tictactoe.dao.PlayerStats;

import java.io.Serializable;

/**
 * DAO pro top score - hráč
 */
public class Player implements Serializable {
  public final String name;
  public final float winRatio;
  public final int turns;
  public final int seconds;

  public Player(String name, float winRatio, int turns, int seconds) {
    this.name = name;
    this.winRatio = winRatio;
    this.turns = turns;
    this.seconds = seconds;
  }

  public static Player fromPlayerStats(PlayerStats stats, String name) {
    return new Player(name, (float) stats.getWins() / (float) stats.getMathes(), stats.getTurns(), stats.getSeconds());
  }



  @Override
  public String toString() {
    return "Player{" +
            "name='" + name + '\'' +
            ", winRatio=" + winRatio +
            ", turns=" + turns +
            ", seconds=" + seconds +
            '}';
  }

  public Float getScore() {
    return winRatio + (float) turns + (float) seconds;
  }

}
