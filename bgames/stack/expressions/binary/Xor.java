package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.value.Value;
import bgames.value.BoolValue;

public class Xor implements BinaryOperator<Value> {
  @Override
  public Value apply(Value first, Value second) {
    BoolValue result = new BoolValue(false);
    if (first instanceof BoolValue) {
      result = result.xor((BoolValue)first);
    }
    if (second instanceof BoolValue) {
      result = result.xor((BoolValue)second);
    }
    return result;
  }
}
