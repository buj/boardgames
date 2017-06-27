package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.stack.expressions.Expression;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;

public class Binary implements Expression {
  private final BinaryOperator<Value> function;
  private final Expression[] operands;
  
  public Binary(BinaryOperator<Value> function, Expression left, Expression right) {
    this.function = function;
    this.operands = new Expression[2];
    this.operands[0] = left;
    this.operands[1] = right;
  }
  
  private class State extends StackState {
    private int step = 0;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      if (step < 2) {
        step += 1;
        return owner.push(new Stack(operands[step - 1].getState(), String.valueOf(step - 1)));
      }
      return owner.pop(function.apply(owner.getValue("0", outside), owner.getValue("1", outside)), outside);
    }
  }
  @Override
  public State getState() {
    return new State();
  }
}
