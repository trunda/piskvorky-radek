package com.gmail.radekzatec77.tictactoe.dao;

public class MatchStats {
  private int wins = 0;
  private int draws = 0;
  private int turns = 0;
  private int seconds = 0;

  public int getWins() {
    return wins;
  }

  public int getDraws() {
    return draws;
  }

  public int getTurns() {
    return turns;
  }

  public void incWins() {
    this.wins += 1;
  }

  public void incDraws() {
    this.draws += 1;
  }

  public void incTurns() {
    this.turns += 1;
  }

  public void incSeconds(int seconds) {
    this.seconds += seconds;
  }

  public int getSeconds() {
    return seconds;
  }
}
