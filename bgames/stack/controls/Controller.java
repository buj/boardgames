package bgames.stack.controls;

import bgames.stack.Executable;
import bgames.other.ParseState;

public interface Controller extends Executable {
  public static Controller parse(ParseState text) {
    Controller result = Block.parse(text);
    if (result == null) {
      result = If.parse(text);
    }
    if (result == null) {
      result = WithBlock.parse(text);
    }
    if (result == null) {
      result = ThingBlock.parse(text);
    }
    return result;
  }
}
