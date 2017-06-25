package bgames.value;

public class IntValue implements Value {
  private int value;
  
  public IntValue(int value) {
    this.value = value;
  }
  public int getValue() {
    return value;
  }
}
