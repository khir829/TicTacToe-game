import java.util.Scanner;

public class TicTacToe {
	private char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' },
			{ ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public void startGame() {
		Scanner scan = new Scanner(System.in);
		int Player1Pos;
		int Player2Pos;
		System.out.println("Top row 1-3 from left to right\n" + "Middle row 4-6 from left to right\n"
				+ "Bottom row 7-9 from left to right");
		printGameBoard(gameBoard);

		System.out.println("Enter your placement (1-9): ");
		Player1Pos = scan.nextInt();

		placePiece(gameBoard, Player1Pos, "player1");
		printGameBoard(gameBoard);

	}

	public void placePiece(char[][] gameBoard, int pos, String user) {

		char symbol = 'X';

		if (user.equals("player1")) {
			symbol = 'X';
		} else {
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
