package bgames.stack.controls;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.stack.expressions.ExpressionList;
import bgames.other.NameList;
import bgames.value.Value;
import bgames.value.ValueList;
import bgames.other.ParseState;

public class WithBlock implements Controller {
  private final NameList names;
  private final ExpressionList list;
  private final Block block;
  
  public WithBlock(NameList names, ExpressionList list, Block block) {
    this.names = names;
    this.list = list;
    this.block = block;
  }
  
  private class State extends StackState {
    private int step = 0;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      step += 1;
      if (step == 1) {
        return owner.push(new Stack(list.getState(), "0"));
      }
      if (step == 2) {
        Value temp = owner.getValue("0", outside);
        if (temp instanceof ValueList) {
          Stack newTop = owner.push(new Stack(block.getState()));
          ValueList values = (ValueList)temp;
          for (int i = 0; i < names.getLength(); i++) {
            newTop = newTop.setNameValue(names.get(i), values.get(i), outside);
          }
          return newTop;
        }
      }
      return owner.pop();
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static WithBlock parse(ParseState text) {
    int backup = text.getPosition();
    if (!text.read("with")) {
      return null;
    }
    NameList names = NameList.parse(text);
    if (names == null) {
      text.setPosition(backup);
      return null;
    }
    if (!text.read("=")) {
      text.setPosition(backup);
      return null;
    }
    ExpressionList exps = ExpressionList.parse(text);
    if (exps == null) {
      text.setPosition(backup);
      return null;
    }
    Block block = Block.parse(text);
    if (block == null) {
      text.setPosition(backup);
      return null;
    }
    return new WithBlock(names, exps, block);
  }
}
