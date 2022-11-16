package game;

import java.util.ArrayList;

public class Player {
  private ArrayList<Integer> playerPosition = new ArrayList<Integer>();
  private String name;
  private char playerSymbol;

  /**
   * If the player is player one, the player has the 'X' symbol, if it is player two, the player has
   * an 'O' symbol
   * 
   * @param number The player number
   */
  public Player(PlayerNumber number) {
    if (number == PlayerNumber.ONE) {
      playerSymbol = 'X';
    } else if (number == PlayerNumber.TWO) {
      playerSymbol = 'O';
    }
  }

  /**
   * Gets all the symbols placed by this player
   * 
   * @return The symbols placed by this player
   */
  public ArrayList<Integer> getPlayerPosition() {
    return playerPosition;
  }

  /**
   * Gets the name of the player
   * 
   * @return The name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the player
   * 
   * @param name The name to be set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the player symbol of the player
   * 
   * @return The symbol
   */
  public char getPlayerSymbol() {
    return playerSymbol;
  }
}
