package strategyAI;

public class StrategyFactory {

	/**
	 * 
	 * This will return the AI strategy according to the user input
	 * 
	 * @param string the user input which will determine the AI strategy
	 * @return the strategy of the AI
	 */
	public static StrategyAI setUp(String string) {
		switch (string) {
		case "U":
			return new UnbeatableStrategy();
		case "N":
			return new RandomStrategy();
		}
		return null;
	}
}
