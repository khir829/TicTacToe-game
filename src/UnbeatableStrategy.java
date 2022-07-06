import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnbeatableStrategy implements StrategyAI {
	private List<List<Integer>> winCondition = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6),
			Arrays.asList(7, 8, 9), Arrays.asList(1, 4, 7), Arrays.asList(2, 5, 8), Arrays.asList(3, 6, 9),
			Arrays.asList(1, 5, 9), Arrays.asList(7, 5, 3));

	@Override
	public int SymbolPlacement(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos) {
		int bestMove = minimax(player1Pos, AIPos).get(1);

		return bestMove;
	}

	/**
	 * This method returns a score based on the positions of the AI and the player
	 * 
	 * @param player1Pos The current positions of symbols of the first player
	 * @param AIPosThe   current positions of symbols of the AI
	 * @return the score of the placement
	 */
	public ArrayList<Integer> minimax(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos) {
		ArrayList<Integer> newPlayerMoves = player1Pos;
		ArrayList<Integer> newAIMoves = AIPos;
		ArrayList<Integer> emptySpots = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();

		ArrayList<Integer> best = new ArrayList<Integer>();
		Map<Integer, Integer> moveList = new HashMap<Integer, Integer>();
		int bestScore;

		for (int i = 1; i <= 9; i++) {
			if (!player1Pos.contains(i) && !AIPos.contains(i)) {
				emptySpots.add(i);
			}
		}
		if (checkWinner(player1Pos)) {
			result.add(-1 * (emptySpots.size() + 1));
			return result;
		} else if (checkWinner(AIPos)) {
			result.add(1 * (emptySpots.size() + 1));
			return result;
		} else if (emptySpots.size() == 0) {
			result.add(0);
			return result;
		}

		for (int i = 0; i < emptySpots.size(); i++) {
			int score;

			if (player1Pos.size() == AIPos.size()) {

				newPlayerMoves.add(emptySpots.get(i));
				score = minimax(newPlayerMoves, AIPos).get(0);
				moveList.put(emptySpots.get(i), score);
				newPlayerMoves.remove(emptySpots.get(i));

			} else {

				newAIMoves.add(emptySpots.get(i));
				score = minimax(player1Pos, newAIMoves).get(0);
				moveList.put(emptySpots.get(i), score);
				newAIMoves.remove(emptySpots.get(i));

			}
		}

		if (player1Pos.size() == AIPos.size()) {
			bestScore = 1000;
			best.add(bestScore);
			best.add(1);
			for (int key : moveList.keySet()) {
				if (moveList.get(key) < bestScore) {
					bestScore = moveList.get(key);
					best.set(0, moveList.get(key));
					best.set(1, key);
				}
			}
		} else {
			bestScore = -1000;
			best.add(bestScore);
			best.add(1);
			for (int key : moveList.keySet()) {
				if (moveList.get(key) > bestScore) {
					bestScore = moveList.get(key);
					best.set(0, moveList.get(key));
					best.set(1, key);
				}
			}
		}
		return best;
	}

	/**
	 * This method checks if there is a winner based on input positions of the
	 * symbols and the winning conditions
	 * 
	 * @param pos the position of the symbols
	 * @return true or false depending on the symbol positions
	 */
	private boolean checkWinner(ArrayList<Integer> pos) {

		for (List<Integer> list : winCondition) {
			if (pos.containsAll(list)) {
				return true;
			}
		}
		return false;
	}

}