import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnbeatableStrategy implements StrategyAI {

	@Override
	public int SymbolPlacement(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos) {
		int bestMove = minimax(player1Pos, AIPos).get(1);

		return bestMove;
	}

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

	private boolean checkWinner(ArrayList<Integer> pos) {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List diagonal1 = Arrays.asList(1, 5, 9);
		List diagonal2 = Arrays.asList(7, 5, 3);

		List<List> winCon = new ArrayList<List>();
		winCon.add(topRow);
		winCon.add(midRow);
		winCon.add(botRow);
		winCon.add(leftCol);
		winCon.add(midCol);
		winCon.add(rightCol);
		winCon.add(diagonal1);
		winCon.add(diagonal2);

		for (List list : winCon) {
			if (pos.containsAll(list)) {
				return true;
			}
		}
		return false;
	}

}