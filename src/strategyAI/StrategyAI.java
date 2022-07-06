package strategyAI;
import java.util.ArrayList;

public interface StrategyAI {
	/**
	 * This method gets the position of symbol placement of the AI based on the
	 * positions of the player and the AI
	 * 
	 * @param player1Pos the current symbol position of the player
	 * @param AIPos      the current symbol position of the AI
	 * @return the position of symbol placement of the AI
	 */
	int SymbolPlacement(ArrayList<Integer> player1Pos, ArrayList<Integer> AIPos);
}
