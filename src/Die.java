import java.util.Random;

public class Die {
  private int currentValue;
  private int numberOfSides;
  private Random randomIntPicker;

  // contructor
  public Die(int currentValue) {
    this.currentValue = currentValue;
    this.numberOfSides = 6;
    this.randomIntPicker = new Random();
  }

  // number of sides getter
  public int getNumberOfSides() {
    return numberOfSides;
  }

  // current value getter
  public int getCurrentValue() {
    return currentValue;
  }

  public void roll() {
    this.currentValue = this.randomIntPicker.nextInt(numberOfSides) + 1;
    System.out.println(getCurrentValue());
  }
}
