import java.util.Random;

public class Die {
  private Random randomIntPicker;
  private int sides;
  private int currentValue;

  // contructor
  public Die(int sides) {
    this.randomIntPicker = new Random();
    this.sides = sides;
    this.currentValue = randomIntPicker.nextInt(sides) + 1;
  }

  // number of sides getter
  public int getSides() {
    return sides;
  }

  // current value getter
  public int getCurrentValue() {
    return currentValue;
  }

  public void roll() {
    this.currentValue = this.randomIntPicker.nextInt(sides) + 1;
    System.out.println(getCurrentValue());
  }
}
