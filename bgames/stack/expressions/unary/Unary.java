package bgames.stack.expressions.unary;

import java.util.function.UnaryOperator;

import bgames.stack.expressions.Expression;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;
import bgames.other.ParseState;

public class Unary implements Expression {
  private final UnaryOperator<Value> function;
  private final Expression first;
  
  public Unary(UnaryOperator<Value> function, Expression first) {
    this.function = function;
    this.first = first;
  }
  
  private class State extends StackState {
    private boolean step = false;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      if (!step) {
        step = true;
        return owner.push(new Stack(first.getState(), "0"));
      }
      return owner.pop(function.apply(owner.getValue("0", outside)), outside);
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static Unary parse(ParseState text) {
    int backup = text.getPosition();
    UnaryOperator<Value> fun = Negated.parse(text);
    if (fun == null) {
      fun = Reflected.parse(text);
    }
    if (fun == null) {
      text.setPosition(backup);
      return null;
    }
    Expression exp = Expression.parse(text);
    if (exp == null) {
      text.setPosition(backup);
      return null;
    }
    return new Unary(fun, exp);
  }
}
