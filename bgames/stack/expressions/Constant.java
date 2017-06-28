package bgames.stack.expressions;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;
import bgames.other.ParseState;

public class Constant implements Expression {
  private final Value value;
  
  public Constant(Value value) {
    this.value = value;
  }
  
  private class State extends StackState {
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      return owner.pop(value, outside);
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static Constant parse(ParseState text) {
    Value val = Value.parse(text);
    if (val == null) {
      return null;
    }
    return new Constant(val);
  }
}
