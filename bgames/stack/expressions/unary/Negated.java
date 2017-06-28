package bgames.stack.expressions.unary;

import java.util.function.UnaryOperator;

import bgames.value.Value;
import bgames.value.BoolValue;
import bgames.other.ParseState;

public class Negated implements UnaryOperator<Value> {
  @Override
  public Value apply(Value operand) {
    if (operand instanceof BoolValue) {
      return ((BoolValue)operand).negated();
    }
    return operand;
  }
  
  public static Negated parse(ParseState text) {
    if (text.read("!")) {
      return new Negated();
    }
    return null;
  }
}
