package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.value.Value;
import bgames.value.BoolValue;
import bgames.other.ParseState;

public class And implements BinaryOperator<Value> {
  @Override
  public Value apply(Value first, Value second) {
    BoolValue result = new BoolValue(true);
    if (first instanceof BoolValue) {
      result = result.and((BoolValue)first);
    }
    if (second instanceof BoolValue) {
      result = result.and((BoolValue)second);
    }
    return result;
  }
  
  public static And parse(ParseState text) {
    if (text.read("&&")) {
      return new And();
    }
    return null;
  }
  
  @Override
  public String toString() {
    return "&&";
  }
}
