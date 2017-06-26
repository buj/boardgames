package bgames.stack.expressions.unary;

import java.util.function.UnaryOperator;

import bgames.stack.expressions.Expression;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;

public class UnaryOperation implements Expression {
  private final UnaryOperator<Value> function;
  private final Expression first;
  
  public UnaryOperation(UnaryOperator<Value> function, Expression first) {
    this.function = function;
    this.first = first;
  }
  
  public static class State extends StackState {
    private final UnaryOperation source;
    private boolean step;
    
    public State(UnaryOperation source) {
      this.source = source;
      this.step = false;
    }
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      if (!step) {
        step = true;
        return owner.push(new Stack(source.first.getState(), "0"));
      }
      return owner.pop(source.function.apply(owner.getValue("0", outside)), outside);
    }
  }
  @Override
  public StackState getState() {
    return new State(this);
  }
}
