package bgames.stack.mutators;

import bgames.stack.Executable;
import bgames.other.ParseState;

public interface Mutator extends Executable {
  public static Mutator parse(ParseState text) {
    Mutator result = Assignment.parse(text);
    if (result == null) {
      result = Return.parse(text);
    }
    return result;
  }
}
