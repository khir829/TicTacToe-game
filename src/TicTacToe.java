import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
	private char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' },
			{ ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };
	private ArrayList<Integer> player1Pos = new ArrayList<Integer>();
	private ArrayList<Integer> player2Pos = new ArrayList<Integer>();

	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public void startGame() {

		boolean flag = true;
		int Player1Pos;
		int Player2Pos;

		System.out.println("Top row 1-3 from left to right\n" + "Middle row 4-6 from left to right\n"
				+ "Bottom row 7-9 from left to right");

		printGameBoard(gameBoard);

		do {
			Scanner scan1 = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);

			System.out.println("Player 1 Enter your placement (1-9): ");
			Player1Pos = scan1.nextInt();

			placePiece(gameBoard, Player1Pos, "player1");
			printGameBoard(gameBoard);

			System.out.println("Player 2 Enter your placement (1-9): ");
			Player2Pos = scan2.nextInt();

			placePiece(gameBoard, Player2Pos, "player2");
			printGameBoard(gameBoard);
		} while (flag);

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
				return "Player 1 Wins";
			} else if (player2Pos.containsAll(list)) {
				return "Player 2 Wins";
			} else if (player1Pos.size() + player2Pos.size() == 9) {
				return "It's a draw";
			}
		}
		return "";
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
