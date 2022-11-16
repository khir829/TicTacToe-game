package strategyAI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import game.GameBoard;

public class UnbeatableStrategy implements StrategyAI {

  private GameBoard board = new GameBoard();

  @Override
  public int SymbolPlacement(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos) {
    int bestMove = minimax(player1Pos, AIPos)[1];
    return bestMove;
  }

  /**
   * This method returns a score based on the positions of the AI and the player
   * 
   * @param player1Pos The current positions of symbols of the first player
   * @param AIPosThe current positions of symbols of the AI
   * @return the score of the placement
   */
  public int[] minimax(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos) {
    ArrayList<Integer> newPlayerMoves = player1Pos;
    ArrayList<Integer> newAIMoves = AIPos;
    ArrayList<Integer> emptySpots = new ArrayList<Integer>();
    Map<Integer, Integer> moveList = new HashMap<Integer, Integer>();

    int[] result = new int[2];
    int[] best = new int[2];
    int bestScore;

    // Create list of positions that are available on the gameboard
    for (int i = 1; i <= 9; i++) {
      if (!player1Pos.contains(i) && !AIPos.contains(i)) {
        emptySpots.add(i);
      }
    }

    // Check if there is a winner or a tie and return appropriate values for the
    // minimax algorithm
    if (checkWinner(player1Pos)) {
      result[0] = (-1 * (emptySpots.size() + 1));
      return result;
    } else if (checkWinner(AIPos)) {
      result[0] = (1 * (emptySpots.size() + 1));
      return result;
    } else if (emptySpots.size() == 0) {
      result[0] = 0;
      return result;
    }

    // Loop through empty positions on board and add AI or player positions on the
    // board recursively
    for (int i = 0; i < emptySpots.size(); i++) {
      int score;

      if (player1Pos.size() == AIPos.size()) {

        newPlayerMoves.add(emptySpots.get(i));
        score = minimax(newPlayerMoves, AIPos)[0];
        moveList.put(emptySpots.get(i), score);
        newPlayerMoves.remove(emptySpots.get(i));

      } else {

        newAIMoves.add(emptySpots.get(i));
        score = minimax(player1Pos, newAIMoves)[0];
        moveList.put(emptySpots.get(i), score);
        newAIMoves.remove(emptySpots.get(i));

      }
    }

    // Get the best score
    if (player1Pos.size() == AIPos.size()) {
      bestScore = 1000;
      for (int key : moveList.keySet()) {
        if (moveList.get(key) < bestScore) {
          bestScore = moveList.get(key);
          best[0] = moveList.get(key);
          best[1] = key;
        }
      }
    } else {
      bestScore = -1000;
      for (int key : moveList.keySet()) {
        if (moveList.get(key) > bestScore) {
          bestScore = moveList.get(key);
          best[0] = moveList.get(key);
          best[1] = key;
        }
      }
    }
    return best;
  }

  /**
   * This method checks if there is a winner based on input positions of the symbols and the winning
   * conditions
   * 
   * @param pos the position of the symbols
   * @return true or false depending on the symbol positions
   */
  public boolean checkWinner(ArrayList<Integer> pos) {
    // Loop through the win condition and compare the positions
    for (List<Integer> list : board.getWinCondition()) {
      if (pos.containsAll(list)) {
        return true;
      }
    }
    return false;
  }

}
