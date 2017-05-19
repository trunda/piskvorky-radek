package com.gmail.radekzatec77.tictactoe.ui;

import com.gmail.radekzatec77.tictactoe.topscore.Player;
import com.gmail.radekzatec77.tictactoe.topscore.Roster;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class TopScoreDialog extends JDialog {

  private final Roster roster;

  public TopScoreDialog(JFrame frame) {
    super(frame, "Nejlepší hráči", true);
    roster = new Roster();
    setResizable(false);
    createTable();
    pack();
  }


  private void createTable() {
    Vector col = new Vector();
    col.add("Jméno");
    col.add("Poměr výher");
    col.add("Počet tahů");
    col.add("Počet sekund");

    Vector rows = new Vector();
    for (Player player : roster.getPlayers()) {
      Vector row = new Vector();
      row.add(player.name);
      row.add(player.winRatio);
      row.add(player.turns);
      row.add(player.seconds);
      rows.add(row);
    }

    JTable table = new JTable(rows, col);
    table.setShowGrid(true);
    table.setShowHorizontalLines(true);
    table.setShowVerticalLines(true);
    table.setEnabled(false);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    c.add(new JScrollPane(table));
  }

}
