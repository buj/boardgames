package bgames.stack.expressions;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;

public class Constant implements Expression {
  private final Value value;
  
  public Constant(Value value) {
    this.value = value;
  }
  
  public static class State extends StackState {
    private final Constant source;
    
    public State(Constant source) {
      this.source = source;
    }
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      return owner.pop(source.value, outside);
    }
  }
  @Override
  public StackState getState() {
    return new State(this);
  }
}
