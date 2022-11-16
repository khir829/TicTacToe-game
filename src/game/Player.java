package game;

import java.util.ArrayList;

public class Player {
  private ArrayList<Integer> playerPosition = new ArrayList<Integer>();
  private String name;
  private char playerSymbol;

  public Player(PlayerNumber number) {
    if (number == PlayerNumber.ONE) {
      playerSymbol = 'X';
    } else if (number == PlayerNumber.TWO) {
      playerSymbol = 'O';
    }
  }

  public ArrayList<Integer> getPlayerPosition() {
    return playerPosition;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public char getPlayerSymbol() {
    return playerSymbol;
  }

  public void setPlayerSymbol(char playerSymbol) {
    this.playerSymbol = playerSymbol;
  }


}
