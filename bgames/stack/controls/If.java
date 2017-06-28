package bgames.stack.controls;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.stack.expressions.Expression;
import bgames.value.Value;
import bgames.value.BoolValue;
import bgames.other.ParseState;

public class If implements Controller {
  private final Expression condition;
  private final Block block;
  
  public If(Expression condition, Block block) {
    this.condition = condition;
    this.block = block;
  }
  
  private class State extends StackState {
    private int step = 0;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outsideWorld) {
      step += 1;
      if (step == 1) {
        return owner.push(new Stack(condition.getState(), "0"));
      }
      if (step == 2) {
        Value value = owner.getValue("0", outsideWorld);
        if (value instanceof BoolValue) {
          if (((BoolValue)value).getValue()) {
            return owner.push(new Stack(block.getState()));
          }
        }
      }
      return owner.pop();
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static If parse(ParseState text) {
    int backup = text.getPosition();
    if (!text.read("if")) {
      return null;
    }
    if (!text.read("(")) {
      text.setPosition(backup);
      return null;
    }
    Expression exp = Expression.parse(text);
    if (exp == null) {
      text.setPosition(backup);
      return null;
    }
    if (!text.read(")")) {
      text.setPosition(backup);
      return null;
    }
    Block block = Block.parse(text);
    if (block == null) {
      text.setPosition(backup);
      return null;
    }
    return new If(exp, block);
  }
}
