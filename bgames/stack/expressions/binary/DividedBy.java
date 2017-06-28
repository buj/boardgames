package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.value.Value;
import bgames.value.IntValue;
import bgames.other.ParseState;

public class DividedBy implements BinaryOperator<Value> {
  @Override
  public Value apply(Value first, Value second) {
    IntValue result = new IntValue(1);
    if (first instanceof IntValue) {
      result = result.times((IntValue)first);
    }
    if (second instanceof IntValue) {
      result = result.dividedBy((IntValue)second);
    }
    return result;
  }
  
  public static DividedBy parse(ParseState text) {
    if (text.read("/")) {
      return new DividedBy();
    }
    return null;
  }
  
  @Override
  public String toString() {
    return "/";
  }
}
