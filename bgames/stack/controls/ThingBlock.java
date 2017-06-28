package bgames.stack.controls;

import java.util.ArrayList;
import java.util.Iterator;

import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.stack.expressions.Expression;
import bgames.value.Value;
import bgames.value.ThingPointer;
import bgames.value.FieldPointer;
import bgames.thing.Thing;
import bgames.other.ParseState;

public class ThingBlock implements Controller {
  private final Expression expression;
  private final Block block;
  
  public ThingBlock(Expression expression, Block block) {
    this.expression = expression;
    this.block = block;
  }
  
  private class State extends StackState {
    private int step = 0;
    
    @Override
    public Stack next(Stack owner, OutsideWorld outside) {
      step += 1;
      if (step == 1) {
        return owner.push(new Stack(expression.getState(), "0"));
      }
      if (step == 2) {
        Value val = owner.getValue("0", outside);
        if (val instanceof ThingPointer) {
          ThingPointer thinger = (ThingPointer)val;
          Thing thing = outside.getThings().getThing(thinger);
          ArrayList<String> fields = new ArrayList<>();
          if (thing != null) {
            fields = thing.getFields().collect();
          }
          Stack newTop = owner.push(new Stack(block.getState()));
          Iterator<String> fielder = fields.iterator();
          while (fielder.hasNext()) {
            String field = fielder.next();
            newTop = newTop.setLocalNameValue(field, thinger.getField(field), outside);
          }
          return newTop;
        }
      }
      return owner.pop();
    }
    
    @Override
    public String toString() {
      String result = ThingBlock.this.toString();
      if (step == 0) {
        result += "\n(thing pointer will be evaluated next)";
      }
      if (step == 1) {
        result += "\n(block will be executed next)";
      }
      if (step == 2) {
        result += "\n(done)";
      }
      return result;
    }
  }
  @Override
  public State getState() {
    return new State();
  }
  
  public static ThingBlock parse(ParseState text) {
    int backup = text.getPosition();
    Expression exp = Expression.parse(text);
    if (exp == null) {
      return null;
    }
    Block block = Block.parse(text);
    if (block == null) {
      text.setPosition(backup);
      return null;
    }
    return new ThingBlock(exp, block);
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(expression.toString());
    builder.append(" ");
    builder.append(block.toString());
    return builder.toString();
  }
}
