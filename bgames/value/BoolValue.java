package bgames.value;

import bgames.other.ParseState;

public class BoolValue implements Value {
  private boolean value;
  
  public BoolValue(boolean value) {
    this.value = value;
  }
  public boolean getValue() {
    return value;
  }
  
  public BoolValue and(BoolValue operand) {
    return new BoolValue(value && operand.value);
  }
  public BoolValue or(BoolValue operand) {
    return new BoolValue(value || operand.value);
  }
  public BoolValue equals(BoolValue operand) {
    return new BoolValue(value == operand.value);
  }
  public BoolValue xor(BoolValue operand) {
    return new BoolValue(value != operand.value);
  }
  public BoolValue negated() {
    return new BoolValue(!value);
  }
  
  public static BoolValue parse(ParseState text) {
    if (text.read("true")) {
      return new BoolValue(true);
    }
    if (text.read("false")) {
      return new BoolValue(false);
    }
    return null;
  }
  
  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
