package com.gmail.radekzatec77.tictactoe.topscore;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Roster {
  private ArrayList<Player> players;

  public Roster() {
    try {
      loadFromFile();
    } catch (Exception ex) {
      players = new ArrayList<>();
    }
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }

  public void addPlayer(Player player) throws IOException {
    players.add(player);
    sort();
    saveToFile();
  }

  private void sort() {
    players.sort(new Comparator<Player>() {
      @Override
      public int compare(Player o1, Player o2) {
        return o1.getScore().compareTo(o2.getScore());
      }
    });
  }

  private void saveToFile() throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("top-players.ser"));
    sort();
    out.writeObject(players);
    out.close();
  }

  private void loadFromFile() throws IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream("top-players.ser"));
    players = (ArrayList<Player>) in.readObject();
  }

  @Override
  public String toString() {
    return "Roster{" +
            "players=" + players +
            '}';
  }
}
