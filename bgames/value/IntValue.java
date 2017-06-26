package bgames.value;

public class IntValue implements Value {
  private int value;
  
  public IntValue(int value) {
    this.value = value;
  }
  public int getValue() {
    return value;
  }
  
  public IntValue plus(IntValue operand) {
    return new IntValue(value + operand.value);
  }
  public IntValue minus(IntValue operand) {
    return new IntValue(value - operand.value);
  }
  public IntValue times(IntValue operand) {
    return new IntValue(value * operand.value);
  }
  public IntValue dividedBy(IntValue operand) {
    if (operand.value != 0) {
      return new IntValue(value / operand.value);
    }
    return this;
  }
}
