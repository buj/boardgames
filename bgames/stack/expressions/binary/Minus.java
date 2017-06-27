package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.value.Value;
import bgames.value.IntValue;

public class Minus implements BinaryOperator<Value> {
  @Override
  public Value apply(Value first, Value second) {
    IntValue result = new IntValue(0);
    if (first instanceof IntValue) {
      result = result.plus((IntValue)first);
    }
    if (second instanceof IntValue) {
      result = result.minus((IntValue)second);
    }
    return result;
  }
}
