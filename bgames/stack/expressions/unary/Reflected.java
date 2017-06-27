package bgames.stack.expressions.binary;

import java.util.function.UnaryOperator;

import bgames.value.Value;
import bgames.value.IntValue;

public class Reflected implements UnaryOperator<Value> {
  @Override
  public Value apply(Value operand) {
    if (operand instanceof IntValue) {
      return ((IntValue)operand).reflected();
    }
    return operand;
  }
}
