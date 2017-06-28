package bgames.stack.expressions;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.stack.controls.WithBlock;
import bgames.value.FieldPointer;
import bgames.field.Field;
import bgames.field.FunctionField;
import bgames.other.ParseState;

public class FunctionCall implements Expression {
  private final String name;
  private final ExpressionList list;
  
  public FunctionCall(String name, ExpressionList list) {
    this.name = name;
    this.list = list;
  }
  
  private class State extends StackState {
    private boolean step = false;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      if (!step) {
        step = true;
        FieldPointer pointer = owner.getPointer(name);
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
    public String toString() {
      String result = FunctionCall.this.toString();
      if (!step) {
        result += "\n(try to call this function)";
      }
      if (step) {
        result += "\n(done)";
      }
      return result;
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
  
  public static FunctionCall parse(ParseState text) {
    int backup = text.getPosition();
    String name = text.readName();
    if (name == null) {
      return null;
    }
    ExpressionList list = ExpressionList.parse(text);
    if (list == null) {
      text.setPosition(backup);
      return null;
    }
    return new FunctionCall(name, list);
  }
  
  @Override
  public String toString() {
    return name + list.toString();
  }
}
