import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UnbeatableStrategy implements StrategyAI {

	@Override
	public int SymbolPlacement(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos) {
		return minimax(player1Pos, AIPos);
	}

	public int minimax(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos) {
		ArrayList<Integer> newPlayerMoves = player1Pos;
		ArrayList<Integer> newAIMoves = AIPos;
		Set<Integer> emptySpots = new HashSet<Integer>();
		Map<Integer, Integer> move = new HashMap<Integer, Integer>();
		int bestScore = -10000;
		int bestMove = 1;

		for (int i = 1; i <= 9; i++) {
			if (!player1Pos.contains(i) && !AIPos.contains(i)) {
				emptySpots.add(i);
			}
		}

		if (checkWinner(player1Pos)) {
			return -1 * (emptySpots.size() + 1);
		} else if (checkWinner(AIPos)) {
			return 1 * (emptySpots.size() + 1);
		} else if (player1Pos.size() + AIPos.size() == 9) {
			return 0;
		}

		for (int i : emptySpots) {
			int score;

			if (player1Pos.size() == AIPos.size()) {
				newPlayerMoves.add(i);
//				System.out.println("P1 " + newPlayerMoves);
				score = minimax(newPlayerMoves, AIPos);
				newPlayerMoves.remove(newPlayerMoves.indexOf(i));
			} else {
				newAIMoves.add(i);
//				System.out.println("AI " + newAIMoves);
				score = minimax(player1Pos, newAIMoves);
				newAIMoves.remove(newAIMoves.indexOf(i));
			}
			move.put(i, score);
		}

		for (int key : move.keySet()) {
			if (move.get(key) > bestScore) {
				bestMove = key;
				bestScore = move.get(key);
			}
		}
		System.out.println(bestMove);
		return bestMove;
	}

//	public void obtainEmptySpots(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos) {
//		for (int i = 1; i <= 9; i++) {
//			if (!player1Pos.contains(i) && !AIPos.contains(i)) {
//				emptySpots.add(i);
//			}
//		}
//	}

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