package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.value.Value;
import bgames.value.BoolValue;
import bgames.value.IntValue;
import bgames.other.ParseState;

public class Equals implements BinaryOperator<Value> {
  @Override
  public Value apply(Value first, Value second) {
    if (first instanceof BoolValue && second instanceof BoolValue) {
      BoolValue result = new BoolValue(true);
      if (first instanceof BoolValue) {
        result = result.xor((BoolValue)first);
      }
      if (second instanceof BoolValue) {
        result = result.xor((BoolValue)second);
      }
      return result;
    }
    if (first instanceof IntValue && second instanceof IntValue) {
      return new BoolValue(((IntValue)first).getValue() == ((IntValue)second).getValue());
    }
    return new BoolValue(false);
  }
  
  public static Equals parse(ParseState text) {
    if (text.read("==")) {
      return new Equals();
    }
    return null;
  }
}
