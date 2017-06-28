package bgames.stack;

import bgames.stack.expressions.Expression;
import bgames.stack.mutators.Mutator;
import bgames.stack.controls.Controller;
import bgames.other.ParseState;

public interface Executable {
  StackState getState();
  
  public static Executable parse(ParseState text) {
    Executable result = Mutator.parse(text);
    if (result == null) {
      result = Controller.parse(text);
    }
    if (result == null) {
      result = Expression.parse(text);
    }
    return result;
  }
}
