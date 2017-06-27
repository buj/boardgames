package bgames.stack.expressions;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;

public class NamedValue implements Expression {
  private final String name;
  
  public NamedValue(String name) {
    this.name = name;
  }
  
  private class State extends StackState {    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      return owner.pop(owner.getValue(name, outside), outside);
    }
  }
  @Override
  public State getState() {
    return new State();
  }
}
