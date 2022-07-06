package strategyAI;
import java.util.ArrayList;

public class AI {

	private StrategyAI strategy;

	/**
	 * This method gets the position of symbol placement of the AI
	 * 
	 * @param player1Pos The current positions of symbols of the first player
	 * @param player2Pos The current positions of symbols of the AI
	 * @return the position of the placement
	 */
	public int placement(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos) {
		return strategy.SymbolPlacement(player1Pos, AIPos);
	}

	/**
	 * This method sets the strategy of the AI
	 * 
	 * @param strategy the strategy of the AI
	 */
	public void setStrategy(StrategyAI strategy) {
		this.strategy = strategy;
	}

}
