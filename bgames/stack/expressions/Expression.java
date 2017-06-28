package bgames.stack.expressions;

import bgames.stack.Executable;
import bgames.other.ParseState;
import bgames.stack.expressions.unary.Unary;
import bgames.stack.expressions.binary.Binary;

public interface Expression extends Executable {
  public static Expression parse(ParseState text) {
    Expression result = Constant.parse(text);
    if (result == null) {
      result = CopyThing.parse(text);
    }
    if (result == null) {
      result = FunctionCall.parse(text);
    }
    if (result == null) {
      result = Unary.parse(text);
    }
    if (result == null) {
      result = Binary.parse(text);
    }
    if (result == null) {
      result = ExpressionList.parse(text);
    }
    if (result == null) {
      result = NamedValue.parse(text);
    }
    return result;
  }
}
