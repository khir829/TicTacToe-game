package game;

import java.util.ArrayList;

public class Player {
  private ArrayList<Integer> playerPosition = new ArrayList<Integer>();
  private String name;

  public ArrayList<Integer> getPlayerPosition() {
    return playerPosition;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


}
