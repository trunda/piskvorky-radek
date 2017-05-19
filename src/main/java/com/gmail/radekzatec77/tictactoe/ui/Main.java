package com.gmail.radekzatec77.tictactoe.ui;

import com.gmail.radekzatec77.tictactoe.Board;
import com.gmail.radekzatec77.tictactoe.Game;
import com.gmail.radekzatec77.tictactoe.Match;
import com.gmail.radekzatec77.tictactoe.StoneColor;
import com.gmail.radekzatec77.tictactoe.dao.PlayerStats;
import com.gmail.radekzatec77.tictactoe.exceptions.FinishedGameException;
import com.gmail.radekzatec77.tictactoe.topscore.Player;
import com.gmail.radekzatec77.tictactoe.topscore.Roster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Hlavní třída - GUI aplikace
 */
public class Main extends JFrame {

  /**
   * Kolik času je na tah
   */
  public static final int TURN_TIMEOUT = 45;
  /**
   * Samotná instance hry
   */
  private final Game game;
  /**
   * Komponenta herní desky
   */
  private final BoardComponent boardComponent;
  /**
   * Komponenta statistik
   */
  private final OverallStatisticsComponent overallStatisticsComponent;

  /**
   * Aktuální timer
   */
  private int turnTimer = TURN_TIMEOUT;

  /**
   * Instance swing časovače
   */
  private Timer timer;
  /**
   * Generátor pseudonáhodných čísel
   */
  private final Random random = new Random();

  private final Roster roster;


  public Main() {

    game = new Game(new Match(new Board(7, 6), 4), 3);

    roster = new Roster();

    boardComponent = new BoardComponent(game.match);
    overallStatisticsComponent = new OverallStatisticsComponent(game);

    Dimension boardSize = boardComponent.getDimension();
    setTitle("Piškvorky");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(boardSize.width + 200, boardSize.height);
    setJMenuBar(createMenu());
    setResizable(false);

    setLayout(new BorderLayout());
    add(createBoardComponent(), BorderLayout.WEST);
    add(createStatistics(), BorderLayout.EAST);
    iniTimer();
    timer.start();
    newGame();
  }

  /**
   * Obnoví celou komponentu
   */
  private void refresh() {
    overallStatisticsComponent.setTimer(turnTimer);
    overallStatisticsComponent.refresh();
    repaint();
  }

  /**
   * Inicializuje timer
   */
  private void iniTimer() {
    timer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        turnTimer -= 1;
        if (turnTimer == 0) {
          Integer[] columns = game.match.board.getFreeColumns();
          makeTurn(columns[random.nextInt(columns.length)]);
        }
        refresh();
      }
    });
  }

  /**
   * Spustíme další zápas
   * @throws FinishedGameException
   */
  private void nextMatch() throws FinishedGameException {
    timer.start();
    boardComponent.drawLineBetween(null);
    game.nextMatch();
  }

  /**
   * Udělá další tah
   * @param column
   */
  private void makeTurn(int column) {
    try {
      StoneColor onTurn = game.match.getPlayerOnTurn();
      game.turn(column);
      // vykreslíme položený kámen
      refresh();
      // resetujeme vždy časovač
      timer.stop();
      game.gameStats.incSeconds(onTurn, TURN_TIMEOUT - turnTimer);
      turnTimer = TURN_TIMEOUT;
      // Pokud je daný zápas ukončen ...
      if (game.match.isFinished()) {
        PlayerStats stats = game.gameStats.getStatsFor(game.match.getWinner());
        // ... a máme výherce, vykresli čáru mdzi výherními kameny
        if (game.match.isWinner()) {
          boardComponent.drawLineBetween(game.match.getWinnerBetween());
        }

        // ... a je ukončena celá hra (vítěz dosáhl 3 vyhraných zápasů) vypiš informace o hře
        if (game.isFinished()) {
          boardComponent.drawLineBetween(game.match.getWinnerBetween());
          String message = String.format("Hru vyhrál %s, vyhrál %d zápasů, prohrál %d zápasů, potřeboval %d tahů a %d vteřin",
                  stats.player.equals(StoneColor.WHITE) ? "ŽLUTÝ" : "ČERVENÝ",
                  stats.getWins(),
                  stats.getMathes() - stats.getWins() - stats.getDraws(),
                  stats.getTurns(),
                  stats.getSeconds()
          );

          JOptionPane.showMessageDialog(null, message, "Výhra", JOptionPane.PLAIN_MESSAGE);

          String name = (String) JOptionPane.showInputDialog(
                  null,
                  "Zadejte vaše jméno pro evidenci nejlepších výsledků",
                  "Vaše jméno?",
                  JOptionPane.PLAIN_MESSAGE);
          roster.addPlayer(Player.fromPlayerStats(stats, name));
          showTopScoreDialog();
          newGame();
        } else {
          // ... pokud není ukončena celá hra, a máme vízěze zápasu, vypiš informace of zápasu
          if (game.match.isWinner()) {
            boardComponent.drawLineBetween(game.match.getWinnerBetween());
            String message = String.format("Zápas vyhrál %s, potřeboval %d tahů a %d vteřin",
                    stats.player.equals(StoneColor.WHITE) ? "ŽLUTÝ" : "ČERVENÝ",
                    stats.getLastMatchStats().getTurns(),
                    stats.getLastMatchStats().getSeconds()
            );
            JOptionPane.showMessageDialog(null, message, "Výhra zápasu", JOptionPane.PLAIN_MESSAGE);
          } else {
            // ... a nemáme vítěze, muselo jít o remízu
            JOptionPane.showMessageDialog(null, "Zápas skončil remízou", "Remíza", JOptionPane.PLAIN_MESSAGE);
          }
          // ... není ukončená celá hra, následuje další zápas
          nextMatch();
        }
      } else {
        // ... není ukončen zápas, tah nevedl k výhře, obnovíme časovač
        timer.start();
      }
      // pro jistotu vykreslíme všechny případné změny
      refresh();
    } catch (Exception e) {
      // ... tato výjimka by nikdy neměla nastat, všechny stavy jsou ošetřeny a na plný sloupec se kámen nedá položit
      e.printStackTrace();
    }

  }

  /**
   * Zobrazí dialog nejlepších hráčů
   */
  private void showTopScoreDialog() {
    timer.stop();
    TopScoreDialog dialog = new TopScoreDialog(null);
    dialog.setVisible(true);
    timer.start();
  }

  /**
   * Vytváří herní desku
   * @return
   */
  private JPanel createBoardComponent() {
    JPanel result = new JPanel();
    result.setPreferredSize(boardComponent.getDimension());
    result.setLayout(new BorderLayout());
    result.add(boardComponent, BorderLayout.CENTER);

    boardComponent.addListener(new ColumnSelectionListener() {
      public void columnSelected(int column) {
        makeTurn(column);
      }
    });

    return result;
  }

  /**
   * Vytváří statistický panel vpravo
   * @return
   */
  private JPanel createStatistics() {
    JPanel result = new JPanel();
    result.setVisible(true);
    result.setPreferredSize(new Dimension(200, boardComponent.getDimension().height));
    result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));

    JLabel label;

    result.add(Box.createRigidArea(new Dimension(0,20)));

    label = new JLabel("Piškvorky");
    label.setFont(label.getFont().deriveFont(18f).deriveFont(Font.BOLD));
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    result.add(label);

    result.add(Box.createRigidArea(new Dimension(0,5)));

    label = new JLabel("Autor: Radek Sedláček");
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    result.add(label);

    Utils.addHorizontalSeparator(result);
    result.add(overallStatisticsComponent);
    return result;
  }

  /**
   * Spuštění nové hry
   */
  private void newGame() {
    // Resetovat hru a herní desku
    boardComponent.drawLineBetween(null);
    game.reset();
    refresh();

    // Resetovate timer
    turnTimer = TURN_TIMEOUT;
    timer.stop();
    timer.start();
  }

  /**
   * Vytvoření menu
   * @return
   */
  private JMenuBar createMenu() {
    JMenuBar bar = new JMenuBar();

    JMenu menu = new JMenu("Piškvorky");
    bar.add(menu);

    JMenuItem item;
    item = new JMenuItem("Nová hra");
    item.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        newGame();
      }
    });
    menu.add(item);

    item = new JMenuItem("Nejlepší hráči");
    item.setAccelerator(KeyStroke.getKeyStroke('T', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showTopScoreDialog();
      }
    });
    menu.add(item);


    menu.addSeparator();

    item = new JMenuItem("Konec");
    item.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    menu.add(item);


    return bar;
  }

  /**
   * Spouští celý program
   * @param args
   */
  public static void main(String[] args) {
    Main game = new Main();
    game.setVisible(true);
  }
}
