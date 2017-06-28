package bgames.stack.expressions.binary;

import java.util.function.BinaryOperator;

import bgames.stack.expressions.Expression;
import bgames.stack.Stack;
import bgames.stack.StackState;
import bgames.stack.OutsideWorld;
import bgames.other.ParseState;
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
    
    @Override
    public String toString() {
      String result = Binary.this.toString();
      if (step == 0) {
        result += "\n(first operand will be evaluated next)";
      }
      if (step == 1) {
        result += "\n(second operand will be evaluated next)";
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
  
  public static Binary parse(ParseState text) {
    int backup = text.getPosition();
    if (!text.read("(")) {
      return null;
    }
    Expression first = Expression.parse(text);
    if (first == null) {
      text.setPosition(backup);
      return null;
    }
    BinaryOperator<Value> fun = GreaterEqual.parse(text);
    if (fun == null) {
      fun = Greater.parse(text);
    }
    if (fun == null) {
      fun = SmallerEqual.parse(text);
    }
    if (fun == null) {
      fun = Smaller.parse(text);
    }
    if (fun == null) {
      fun = Equals.parse(text);
    }
    if (fun == null) {
      fun = And.parse(text);
    }
    if (fun == null) {
      fun = Or.parse(text);
    }
    if (fun == null) {
      fun = Xor.parse(text);
    }
    if (fun == null) {
      fun = Plus.parse(text);
    }
    if (fun == null) {
      fun = Minus.parse(text);
    }
    if (fun == null) {
      fun = Times.parse(text);
    }
    if (fun == null) {
      fun = DividedBy.parse(text);
    }
    if (fun == null) {
      fun = Modulo.parse(text);
    }
    if (fun == null) {
      text.setPosition(backup);
      return null;
    }
    Expression second = Expression.parse(text);
    if (second == null) {
      text.setPosition(backup);
      return null;
    }
    if (!text.read(")")) {
      text.setPosition(backup);
      return null;
    }
    return new Binary(fun, first, second);
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("(");
    builder.append(operands[0].toString());
    builder.append(" ");
    builder.append(function.toString());
    builder.append(" ");
    builder.append(operands[1].toString());
    builder.append(")");
    return builder.toString();
  }
}
