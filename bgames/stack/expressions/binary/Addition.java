package bgames.stack.expressions.binary;

import bgames.stack.expressions.Expression;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;
import bgames.value.IntValue;

public class Addition extends BinaryOperation {

  public Addition(Expression left, Expression right) {
    super(left, right);
  }
  
  public static class State extends BinaryOperation.State {

    public State(Addition source) {
      super(source);
    }
    
    @Override
    public IntValue evaluate(Value first, Value second) {
      IntValue result = new IntValue(0);
      if (first instanceof IntValue) {
        result = result.plus((IntValue)first);
      }
      if (second instanceof IntValue) {
        result = result.plus((IntValue)second);
      }
      return result;
    }
  }
  @Override
  public StackState getState() {
    return new State(this);
  }
}
