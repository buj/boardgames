package bgames.value;

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
}
