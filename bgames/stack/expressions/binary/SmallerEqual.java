package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.value.Value;
import bgames.value.IntValue;
import bgames.value.BoolValue;
import bgames.other.ParseState;

public class SmallerEqual implements BinaryOperator<Value> {
  @Override
  public Value apply(Value first, Value second) {
    if (!(first instanceof IntValue && second instanceof IntValue)) {
      return new BoolValue(false);
    }
    return new BoolValue(((IntValue)first).getValue() <= ((IntValue)second).getValue());
  }
  
  public static SmallerEqual parse(ParseState text) {
    if (text.read("<=")) {
      return new SmallerEqual();
    }
    return null;
  }
  
  @Override
  public String toString() {
    return "<=";
  }
}
