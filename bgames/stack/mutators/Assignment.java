package bgames.stack.mutators;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.stack.Executable;
import bgames.stack.expressions.Expression;
import bgames.value.Value;

public class Assignment implements Executable {
  private final String name;
  private final Expression expression;
  
  public Assignment(String name, Expression expression) {
    this.name = name;
    this.expression = expression;
  }
  
  private class State extends StackState {
    private boolean step = false;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      if (!step) {
        step = true;
        return owner.push(new Stack(expression.getState(), "0"));
      }
      Value value = owner.getValue("0", outside);
      return owner.pop().setNameValue(name, value, outside);
    }
  }
  @Override
  public State getState() {
    return new State();
  }
}
