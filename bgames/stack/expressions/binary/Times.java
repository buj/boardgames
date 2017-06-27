package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.value.Value;
import bgames.value.IntValue;

public class Times implements BinaryOperator<Value> {
  @Override
  public Value apply(Value first, Value second) {
    IntValue result = new IntValue(1);
    if (first instanceof IntValue) {
      result = result.times((IntValue)first);
    }
    if (second instanceof IntValue) {
      result = result.times((IntValue)second);
    }
    return result;
  }
}
