package bgames.stack.mutators;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.stack.expressions.Expression;
import bgames.value.Value;
import bgames.other.ParseState;

public class Assignment implements Mutator {
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
  
  public static Assignment parse(ParseState text) {
    int backup = text.getPosition();
    String name = text.readName();
    if (name == null) {
      return null;
    }
    if (!text.read("=")) {
      text.setPosition(backup);
      return null;
    }
    Expression exp = Expression.parse(text);
    if (exp == null) {
      text.setPosition(backup);
      return null;
    }
    return new Assignment(name, exp);
  }
}
