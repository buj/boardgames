package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.value.Value;
import bgames.value.BoolValue;
import bgames.other.ParseState;

public class Or implements BinaryOperator<Value> {
  @Override
  public Value apply(Value first, Value second) {
    BoolValue result = new BoolValue(false);
    if (first instanceof BoolValue) {
      result = result.or((BoolValue)first);
    }
    if (second instanceof BoolValue) {
      result = result.or((BoolValue)second);
    }
    return result;
  }
  
  public static Or parse(ParseState text) {
    if (text.read("||")) {
      return new Or();
    }
    return null;
  }
}
