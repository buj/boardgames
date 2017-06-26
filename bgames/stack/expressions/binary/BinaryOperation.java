package bgames.stack.expressions.binary;

import bgames.stack.expressions.Expression;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;

public abstract class BinaryOperation implements Expression {
  private final Expression[] operands;
  
  public BinaryOperation(Expression left, Expression right) {
    operands = new Expression[2];
    operands[0] = left;
    operands[1] = right;
  }
  
  public static abstract class State extends StackState {
    private final BinaryOperation source;
    private int step;
    
    public State(BinaryOperation source) {
      this.source = source;
      this.step = 0;
    }
    
    public abstract Value evaluate(Value first, Value second);
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      if (step < 2) {
        step += 1;
        return owner.push(new Stack(source.operands[step - 1].getState(), String.valueOf(step - 1)));
      }
      return owner.pop(evaluate(owner.getValue("0", outside), owner.getValue("1", outside)), outside);
    }
  }
}
