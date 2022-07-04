import java.util.ArrayList;

public class AI {

	private StrategyAI strategy;

	public AI() {
		super();
	}

	public int placement(ArrayList<Integer> player1Pos, ArrayList<Integer> player2Pos) {
		return strategy.SymbolPlacement(player1Pos, player2Pos);
	}

	public void setStrategy(StrategyAI strategy) {
		this.strategy = strategy;
	}

}
