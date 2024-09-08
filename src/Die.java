import java.util.Random;

public class Die {
  private int currentValue;
  private int sides;

  // empty constructor
  public Die() {

  }

  // constructor with argument
  public Die(int sides) {
    this.currentValue = 1;
    this.sides = sides;
  }

  // number of sides getter
  public int getSides() {
    return sides;
  }

  // current value getter
  public int getCurrentValue() {
    return currentValue;
  }

  private static Random randomIntPicker = new Random();

  public void roll() {
    this.currentValue = randomIntPicker.nextInt(sides) + 1;
  }
}
