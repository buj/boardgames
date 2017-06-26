package bgames.stack.expressions.binary;

import bgames.stack.expressions.Expression;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;
import bgames.value.IntValue;

public class Division extends BinaryOperation {

  public Division(Expression left, Expression right) {
    super(left, right);
  }
  
  public static class State extends BinaryOperation.State {

    public State(Division source) {
      super(source);
    }
    
    @Override
    public IntValue evaluate(Value first, Value second) {
      IntValue result = new IntValue(1);
      if (first instanceof IntValue) {
        result = result.times((IntValue)first);
      }
      if (second instanceof IntValue) {
        result = result.dividedBy((IntValue)second);
      }
      return result;
    }
  }
  @Override
  public StackState getState() {
    return new State(this);
  }
}
