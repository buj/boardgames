package bgames.stack.expressions;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.stack.controls.WithBlock;
import bgames.other.FieldPointer;
import bgames.field.Field;
import bgames.field.FunctionField;

public class FunctionCall implements Expression {
  private final FieldPointer pointer;
  private final ExpressionList list;
  
  public FunctionCall(FieldPointer pointer, ExpressionList list) {
    this.pointer = pointer;
    this.list = list;
  }
  
  private class State extends StackState {
    private boolean step = false;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      if (!step) {
        step = true;
        Field field = outside.getThings().getField(pointer);
        if (!(field instanceof FunctionField)) {
          return owner.pop();
        }
        FunctionField funField = (FunctionField)field;
        WithBlock executor = new WithBlock(funField.getNames(), list, funField.getBlock());
        return owner.push(new Stack(executor.getState(), "0"));
      }
      return owner.pop(owner.getValue("0", outside), outside);
    }
    
    @Override
    public boolean isReturnPoint() {
      return true;
    }
  }
  @Override
  public State getState() {
    return new State();
  }
}
