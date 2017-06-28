package bgames.value;

import bgames.other.ParseState;

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
  public IntValue modulo(IntValue operand) {
    if (operand.value != 0) {
      return new IntValue(value % operand.value);
    }
    return this;
  }
  public IntValue reflected() {
    return new IntValue(-value);
  }
  
  public static IntValue parse(ParseState text) {
    int res = text.readUint();
    if (res != -1) {
      return new IntValue(res);
    }
    return null;
  }
  
  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
