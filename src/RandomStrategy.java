import java.util.ArrayList;
import java.util.Random;

public class RandomStrategy implements StrategyAI {

	@Override
	public int SymbolPlacement(ArrayList<Integer> player1Pos, ArrayList<Integer> player2Pos) {
		int placement = 1;

		/*
		 * Get a random position on the gameboard and place the symbol there. If the
		 * space is already taken, get another position
		 */
		do {
			Random rand = new Random();
			placement = rand.nextInt(8) + 1;
		} while (player1Pos.contains(placement) || player2Pos.contains(placement));

		return placement;
	}

}
