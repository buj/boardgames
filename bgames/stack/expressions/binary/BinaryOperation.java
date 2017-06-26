package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.stack.expressions.Expression;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;

public abstract class BinaryOperation implements Expression {
  private final BinaryOperator<Value> function;
  private final Expression[] operands;
  
  public BinaryOperation(BinaryOperator<Value> function, Expression left, Expression right) {
    this.function = function;
    this.operands = new Expression[2];
    this.operands[0] = left;
    this.operands[1] = right;
  }
  
  public static class State extends StackState {
    private final BinaryOperation source;
    private int step;
    
    public State(BinaryOperation source) {
      this.source = source;
      this.step = 0;
    }
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      if (step < 2) {
        step += 1;
        return owner.push(new Stack(source.operands[step - 1].getState(), String.valueOf(step - 1)));
      }
      return owner.pop(source.function.apply(owner.getValue("0", outside), owner.getValue("1", outside)), outside);
    }
  }
}
