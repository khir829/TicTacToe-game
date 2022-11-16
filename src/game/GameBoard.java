package game;

import java.util.Arrays;
import java.util.List;

public class GameBoard {
  private char[][] cleanBoard = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
      {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};
  private char[][] gameBoard = new char[5][5];
  private List<List<Integer>> winCondition =
      Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9),
          Arrays.asList(1, 4, 7), Arrays.asList(2, 5, 8), Arrays.asList(3, 6, 9),
          Arrays.asList(1, 5, 9), Arrays.asList(7, 5, 3));

  public char[][] getCleanBoard() {
    return cleanBoard;
  }

  public char[][] getGameBoard() {
    return gameBoard;
  }

  public List<List<Integer>> getWinCondition() {
    return winCondition;
  }
}
