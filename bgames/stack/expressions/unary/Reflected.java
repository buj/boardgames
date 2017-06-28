package bgames.stack.expressions.unary;

import java.util.function.UnaryOperator;

import bgames.value.Value;
import bgames.value.IntValue;
import bgames.other.ParseState;

public class Reflected implements UnaryOperator<Value> {
  @Override
  public Value apply(Value operand) {
    if (operand instanceof IntValue) {
      return ((IntValue)operand).reflected();
    }
    return operand;
  }
  
  public static Reflected parse(ParseState text) {
    if (text.read("-")) {
      return new Reflected();
    }
    return null;
  }
  
  @Override
  public String toString() {
    return "-";
  }
}
