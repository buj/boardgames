package bgames.stack.expressions;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.other.ParseState;

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
    
    @Override
    public String toString() {
      return "get the value of: " + NamedValue.this.toString();
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static NamedValue parse(ParseState text) {
    String res = text.readName();
    if (res != null) {
      return new NamedValue(res);
    }
    return null;
  }
  
  @Override
  public String toString() {
    return name;
  }
}
