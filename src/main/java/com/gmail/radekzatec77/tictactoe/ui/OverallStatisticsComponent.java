package com.gmail.radekzatec77.tictactoe.ui;

import com.gmail.radekzatec77.tictactoe.Game;
import com.gmail.radekzatec77.tictactoe.StoneColor;

import javax.swing.*;
import java.awt.*;

/**
 * Zobrazuje statistiku zápasu a časovač
 */
public class OverallStatisticsComponent extends JPanel {
  private final Game game;
  private JLabel playerBlackScore;
  private JLabel playerWhiteScore;
  private JLabel timerLable;
  private JLabel onTurnIs;

  public OverallStatisticsComponent(Game game) {
    this.game = game;
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(getScorePanel());
    Utils.addHorizontalSeparator(this);
    this.add(getTimerPanel());
  }

  /**
   * Obnoví všechny hodnoty
   */
  public void refresh() {
    playerBlackScore.setText(String.valueOf(game.gameStats.getWins(StoneColor.BLACK)));
    playerWhiteScore.setText(String.valueOf(game.gameStats.getWins(StoneColor.WHITE)));
    onTurnIs.setText(getOnTurn());
  }

  /**
   * Textová reprezentace hráče na tahu
   * @return
   */
  private String getOnTurn() {
    return game.match.getPlayerOnTurn().equals(StoneColor.BLACK) ? "červený" : "žlutý";
  }

  /**
   * Změní časovač
   * @param seconds
   */
  public void setTimer(int seconds) {
    if (seconds <= 5) {
      timerLable.setForeground(Color.RED);
    } else {
      timerLable.setForeground(Color.BLACK);
    }
    timerLable.setText(seconds + " s");
  }

  /**
   * Panel s časovačem a uživatelem na tahu
   * @return
   */
  private JPanel getTimerPanel() {
    JPanel result = new JPanel();
    result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));

    JLabel label;
    label = new JLabel("Do konce tahu");
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    result.add(label);
    result.add(Box.createRigidArea(new Dimension(0,5)));
    timerLable = new JLabel("45 s");
    timerLable.setFont(label.getFont().deriveFont(20f).deriveFont(Font.BOLD));
    timerLable.setAlignmentX(Component.CENTER_ALIGNMENT);
    result.add(timerLable);
    result.add(Box.createRigidArea(new Dimension(0,5)));
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    label = new JLabel("Na tahu je");
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    result.add(label);
    result.add(Box.createRigidArea(new Dimension(0,5)));

    onTurnIs = new JLabel(getOnTurn());
    onTurnIs.setFont(label.getFont().deriveFont(20f).deriveFont(Font.BOLD));
    onTurnIs.setAlignmentX(Component.CENTER_ALIGNMENT);
    result.add(onTurnIs);

    return result;
  }

  /**
   * Panel s celkovým score
   * @return
   */
  private JPanel getScorePanel() {
    JPanel result = new JPanel();
    result.setLayout(new BoxLayout(result, BoxLayout.X_AXIS));

    JLabel label;

    playerBlackScore = new JLabel("0");
    playerBlackScore.setFont(playerBlackScore.getFont().deriveFont(30f).deriveFont(Font.BOLD));
    playerBlackScore.setForeground(Color.decode("#8c0a0f"));
    playerBlackScore.setAlignmentX(Component.CENTER_ALIGNMENT);
    playerBlackScore.setOpaque(true);
    result.add(playerBlackScore);

    result.add(Box.createRigidArea(new Dimension(10,0)));
    label = new JLabel(":");
    label.setFont(label.getFont().deriveFont(30f).deriveFont(Font.BOLD));
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setOpaque(true);
    result.add(label);
    result.add(Box.createRigidArea(new Dimension(10,0)));

    playerWhiteScore = new JLabel("0");
    playerWhiteScore.setFont(playerWhiteScore.getFont().deriveFont(30f).deriveFont(Font.BOLD));
    playerWhiteScore.setForeground(Color.decode("#a19403"));
    playerWhiteScore.setAlignmentX(Component.CENTER_ALIGNMENT);
    playerWhiteScore.setOpaque(true);
    result.add(playerWhiteScore);

    return result;
  }


}
