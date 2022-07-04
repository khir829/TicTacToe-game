import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToe {
	private char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' },
			{ ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };
	private ArrayList<Integer> player1Pos = new ArrayList<Integer>();
	private ArrayList<Integer> player2Pos = new ArrayList<Integer>();
	private boolean gameEnd = false;

	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public ArrayList<Integer> getPlayer1Pos() {
		return player1Pos;
	}

	public ArrayList<Integer> getPlayer2Pos() {
		return player2Pos;
	}

	public void startGame() {

		String gameMode;

		System.out.println("Welcome to the Tic Tac Toe game application!");

		System.out.println("Top row 1-3 from left to right\n" + "Middle row 4-6 from left to right\n"
				+ "Bottom row 7-9 from left to right");

		gameMode = getGameMode();

		if (gameMode.equals("p")) {
			printGameBoard(gameBoard);
			twoPlayerGameMode();
		} else {
			AI ai = new AI();
			String strategy = getAIStrategy();
			if (strategy.equals("n")) {
				ai.setStrategy(new RandomStrategy());
			} else {
				ai.setStrategy(new UnbeatableStrategy());
			}
			printGameBoard(gameBoard);
			aiGameMode(ai);
		}

	}

	private String getAIStrategy() {
		System.out.println("Choose AI difficulty: Normal (N) - Unbeatable (U)");
		String result = Main.scanner.next().toLowerCase();
		while (!result.equals("n") && !result.equals("u")) {
			System.out.println("Please type either 'N' or 'U' for Normal difficulty or Unbeatable difficulty");
			result = Main.scanner.next();
		}
		return result;
	}

	public String getGameMode() {
		String result;
		System.out.println("Choose game mode: Two player game (P) - Play against AI (A)");
		result = Main.scanner.next();
		while (!result.equals("p") && !result.equals("a")) {
			System.out.println("Please type either 'P' or 'A' to have a two player game or match against an AI");
			result = Main.scanner.next();
		}
		return result.toLowerCase();
	}

	public void aiGameMode(AI ai) {
		boolean flag = true;
		do {
			getPlacement("Player 1");
			if (gameEnd) {
				flag = false;
				System.out.println(checkWinner());
				break;
			}

			placePiece(gameBoard, ai.placement(player1Pos, player2Pos), "AI");
			checkWinner();
			System.out.println("\nAI makes a move\n");
			printGameBoard(gameBoard);

			if (gameEnd) {
				flag = false;
				System.out.println(checkWinner());
				break;
			}
		} while (flag);
	}

	public void twoPlayerGameMode() {
		boolean flag = true;
		do {
			getPlacement("Player 1");
			if (gameEnd) {
				flag = false;
				System.out.println(checkWinner());
				break;
			}

			getPlacement("Player 2");
			if (gameEnd) {
				flag = false;
				System.out.println(checkWinner());
				break;
			}
		} while (flag);
	}

	public void getPlacement(String player) {

		int pos;
		System.out.println("\n" + player + " Enter your placement (1-9): \n");
		pos = Main.scanner.nextInt();

		while (player1Pos.contains(pos) || player2Pos.contains(pos) || pos < 1 || pos > 9) {
			printGameBoard(gameBoard);
			System.out.println("Enter a valid position");
			pos = Main.scanner.nextInt();
		}

		placePiece(gameBoard, pos, player);
		checkWinner();
		printGameBoard(gameBoard);
	}

	public String checkWinner() {
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
			if (player1Pos.containsAll(list)) {
				gameEnd = true;
				return "Player 1 Wins!";
			} else if (player2Pos.containsAll(list)) {
				gameEnd = true;
				return "Player 2 Wins!";
			} else if (player1Pos.size() + player2Pos.size() == 9) {
				gameEnd = true;
				return "It's a draw!";
			}
		}
		return "";
	}

	public void placePiece(char[][] gameBoard, int pos, String user) {

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
