package game;

import java.util.ArrayList;
import java.util.List;
import strategyAI.AI;
import strategyAI.StrategyAI;
import strategyAI.StrategyFactory;

public class TicTacToe {
  private GameBoard board = new GameBoard();
  private ArrayList<Integer> player1Pos = new ArrayList<Integer>();
  private ArrayList<Integer> player2Pos = new ArrayList<Integer>();
  private AI ai = new AI();
  private String strategy;
  private StrategyAI strategyOfAI;
  private boolean gameEnd = false;

  /**
   * This method prints out the game board state in a human readable way
   * 
   * @param gameBoard the state of the gamme
   */
  public static void printGameBoard(char[][] board) {
    for (char[] row : board) {
      for (char c : row) {
        System.out.print(c);
      }
      System.out.println();
    }
  }

  /**
   * This method starts the Tic Tac Toe game
   */
  public void startGame() {

    boolean confirmation = false;
    String gameMode;
    String userDecision;

    // Welcome and initialisation message
    System.out.println("Welcome to the Tic Tac Toe game application!");
    System.out.println("Top row 1-3 from left to right\n" + "Middle row 4-6 from left to right\n"
        + "Bottom row 7-9 from left to right");

    gameMode = getGameMode();

    // Set clean board
    setNewBoard();

    // A 2 player game or an AI game is determined by user input.
    // If it is a AI game, the AI is initialised
    if (gameMode.equals("p")) {
      printGameBoard(board.getGameBoard());
      selectedGameMode(null);
    } else {
      strategy = getAIStrategy();
      strategyOfAI = StrategyFactory.setUp(strategy);
      ai.setStrategy(strategyOfAI);
      printGameBoard(board.getGameBoard());
      selectedGameMode(ai);
    }

    // Clear both player's positions to prepare for next game
    player1Pos.clear();
    player2Pos.clear();
    gameEnd = false;

    do {
      System.out.println("Play again? (Y/N)");
      userDecision = Main.scanner.next().toUpperCase();

      if (userDecision.equals("Y")) {
        startGame();
      } else if (userDecision.equals("N")) {
        confirmation = false;
        System.out.println("Terminating...");
      } else {
        confirmation = true;
        System.out.println("Please enter 'Y' or 'N'");
      }
    } while (confirmation);
  }

  /**
   * This method obtains the AI strategy based on user input
   * 
   * @return the selected AI strategy
   */
  private String getAIStrategy() {
    System.out.println("Choose AI difficulty: Normal (N) - Unbeatable (U)");
    String result = Main.scanner.next().toUpperCase();
    while (!result.equals("N") && !result.equals("U")) {
      System.out
          .println("Please type either 'N' or 'U' for Normal difficulty or Unbeatable difficulty");
      result = Main.scanner.next();
    }
    return result;
  }

  /**
   * This method sets the gameboard to a new state
   */
  private void setNewBoard() {
    for (int i = 0; i < board.getCleanBoard().length; i++) {
      for (int j = 0; j < board.getCleanBoard()[i].length; j++) {
        board.getGameBoard()[i][j] = board.getCleanBoard()[i][j];
      }
    }
  }


  /**
   * This method determines whether the Tic Tac Toe game is played with an AI or with 2 players
   * 
   * @return the selected game mode
   */
  private String getGameMode() {
    String result;
    System.out.println("Choose game mode: Two player game (P) - Play against AI (A)");
    result = Main.scanner.next();
    while (!result.equals("p") && !result.equals("a")) {
      System.out.println(
          "Please type either 'P' or 'A' to have a two player game or match against an AI");
      result = Main.scanner.next();
    }
    return result.toLowerCase();
  }

  /**
   * This method initialises the game when based on game mode
   * 
   * (2 player or against AI)
   * 
   * @param ai the AI with a given strategy
   */
  private void selectedGameMode(AI ai) {
    boolean flag = true;
    String otherPlayer = "Player 2";

    if (ai != null) {
      otherPlayer = "AI";
    }

    do {
      getPlacement("Player 1", null);
      if (gameEnd) {
        flag = false;
        System.out.println("Player 1 " + player1Pos);
        System.out.println(otherPlayer + " " + player2Pos);
        System.out.println(checkWinner(otherPlayer));
        break;
      }

      getPlacement(otherPlayer, ai);
      if (gameEnd) {
        flag = false;
        System.out.println("Player 1 " + player1Pos);
        System.out.println(otherPlayer + " " + player2Pos);
        System.out.println(checkWinner(otherPlayer));
        break;
      }
    } while (flag);
  }

  /**
   * This method obtains the placement of a symbol by the player
   * 
   * @param player the current player
   */
  private void getPlacement(String player, AI ai) {
    int pos;
    if (ai == null) {
      System.out.println("\n" + player + " Enter your placement (1-9): \n");
      pos = Main.scanner.nextInt();

      while (player1Pos.contains(pos) || player2Pos.contains(pos) || pos < 1 || pos > 9) {
        printGameBoard(board.getGameBoard());
        System.out.println("Enter a valid position");
        pos = Main.scanner.nextInt();
      }
      placePiece(board.getGameBoard(), pos, player);
      checkWinner("player");
      printGameBoard(board.getGameBoard());
    } else {
      placePiece(board.getGameBoard(), ai.placement(player1Pos, player2Pos), "AI");
      checkWinner("AI");
      System.out.println("\nAI makes a move\n");
      printGameBoard(board.getGameBoard());
    }
  }

  /**
   * This method check who the winner is. If there is no winner, it is a draw
   * 
   * @return The message stating who won or if it was a draw
   */
  private String checkWinner(String otherPlayer) {

    for (List<Integer> list : board.getWinCondition()) {
      if (player1Pos.containsAll(list)) {
        gameEnd = true;
        return "Player 1 Wins!";
      } else if (player2Pos.containsAll(list)) {
        gameEnd = true;
        return otherPlayer + " Wins!";
      }
    }
    if (player1Pos.size() + player2Pos.size() == 9) {
      gameEnd = true;
      return "It's a draw!";
    }
    return "";
  }

  /**
   * This method places the symbol on the Tic Tac Toe board
   * 
   * @param gameBoard the current state of the game board
   * @param pos the position of the placement
   * @param user the player
   */
  private void placePiece(char[][] gameBoard, int pos, String user) {

    char symbol = 'X';

    if (user.equals("Player 1")) {
      player1Pos.add(pos);
      symbol = 'X';
    } else {
      player2Pos.add(pos);
      symbol = 'O';
    }

    switch (pos) {
      case 1:
        gameBoard[0][0] = symbol;
        break;
      case 2:
        gameBoard[0][2] = symbol;
        break;
      case 3:
        gameBoard[0][4] = symbol;
        break;
      case 4:
        gameBoard[2][0] = symbol;
        break;
      case 5:
        gameBoard[2][2] = symbol;
        break;
      case 6:
        gameBoard[2][4] = symbol;
        break;
      case 7:
        gameBoard[4][0] = symbol;
        break;
      case 8:
        gameBoard[4][2] = symbol;
        break;
      case 9:
        gameBoard[4][4] = symbol;
        break;
    }
  }

}
