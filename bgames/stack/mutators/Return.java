package bgames.stack.mutators;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.stack.expressions.Expression;
import bgames.other.ParseState;

public class Return implements Mutator {
  private final Expression expression;
  
  public Return(Expression expression) {
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
      Stack newTop = owner;
      while (!newTop.isReturnPoint()) {
        newTop = newTop.pop();
      }
      return newTop.pop(owner.getValue("0", outside), outside);
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static Return parse(ParseState text) {
    int backup = text.getPosition();
    if (!text.read("return")) {
      return null;
    }
    Expression exp = Expression.parse(text);
    if (exp == null) {
      text.setPosition(backup);
      return null;
    }
    return new Return(exp);
  }
}
