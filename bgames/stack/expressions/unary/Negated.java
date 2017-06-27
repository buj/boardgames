package bgames.stack.expressions.binary;

import java.util.function.UnaryOperator;

import bgames.value.Value;
import bgames.value.BoolValue;

public class Negated implements UnaryOperator<Value> {
  @Override
  public Value apply(Value operand) {
    if (operand instanceof BoolValue) {
      return ((BoolValue)operand).negated();
    }
    return operand;
  }
}
