package bgames.stack.expressions;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;

public class ConstantValue implements Expression {
  private final Value value;
  
  public ConstantValue(Value value) {
    this.value = value;
  }
  
  public static class State extends StackState {
    private final ConstantValue source;
    
    public State(ConstantValue source) {
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
