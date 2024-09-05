import java.util.ArrayList;

public class Player {
  private String name;
  private int score;
  private ArrayList<Die> dice;

  // constructor
  public Player(String name) {
    this.name = name;
    this.score = 0;
    this.dice = new ArrayList<Die>();
  }

  // name getter
  public String getName() {
    return name;
  }

  // points getter 
  public int getScore() {
    return score;
  }

  // method for rolling all dice
  public void rollDice() {
    for (Die die : dice) {
      die.roll();
    }
  }

  public int getDieValue() {
    int total = 0;
    for (Die die : dice) {
      total += die.getCurrentValue();
    }
    return total;
  }

  public void increaseScore() {
    this.score += 1;
  }

  public void addDie(int sides) {
    Die die = new Die(sides);
    this.dice.add(die);
  }
}
