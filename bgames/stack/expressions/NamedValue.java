package bgames.stack.expressions;

import bgames.stack.Executable;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;

public class NamedValue implements Executable {
  private final String name;
  
  public NamedValue(String name) {
    this.name = name;
  }
  
  public static class State extends StackState {
    private final NamedValue source;
    
    public State(NamedValue source) {
      this.source = source;
    }
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      return owner.pop(owner.getValue(source.name, outside), outside);
    }
  }
  @Override
  public StackState getState() {
    return new State(this);
  }
}
