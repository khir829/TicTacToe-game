package game;

import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// initialise and start Tic Tac Toe game
		TicTacToe game = new TicTacToe();
		game.startGame();
	}

}
