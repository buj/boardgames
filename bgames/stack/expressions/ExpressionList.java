package bgames.stack.expressions;

import java.util.ArrayList;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.value.Value;
import bgames.value.ValueList;
import bgames.other.ParseState;

public class ExpressionList implements Expression {
  private final Expression[] list;
  
  public ExpressionList(Expression[] list) {
    this.list = list;
  }
  
  private class State extends StackState {
    private int step = 0;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      if (step < list.length) {
        step += 1;
        return owner.push(new Stack(list[step - 1].getState(), String.valueOf(step - 1)));
      }
      Value[] values = new Value[list.length];
      for (int i = 0; i < list.length; i++) {
        values[i] = owner.getValue(String.valueOf(i), outside);
      }
      return owner.pop(new ValueList(values), outside);
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static ExpressionList parse(ParseState text) {
    int backup = text.getPosition();
    if (!text.read("(")) {
      return null;
    }
    ArrayList<Expression> list = new ArrayList<>();
    if (!text.read(")")) {
      while (true) {
        Expression exp = Expression.parse(text);
        if (exp == null) {
          text.setPosition(backup);
          return null;
        }
        list.add(exp);
        if (text.read(")")) {
          break;
        }
        if (!text.read(",")) {
          text.setPosition(backup);
          return null;
        }
      }
    }
    return new ExpressionList(list.toArray(new Expression[0]));
  }
}
