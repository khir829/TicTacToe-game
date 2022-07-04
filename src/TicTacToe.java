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
		System.out.println("Top row 1-3 from left to right\n" + "Middle row 4-6 from left to right\n"
				+ "Bottom row 7-9 from left to right");
		printGameBoard(gameBoard);
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your placement (1-9): ");
		int pos = scan.nextInt();
		System.out.println(pos);
	}

}
