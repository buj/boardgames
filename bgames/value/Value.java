package bgames.value;

import bgames.other.ParseState;

public interface Value {
  public static Value parse(ParseState text) {
    int backup = text.getPosition();
    Value result = BoolValue.parse(text);
    if (result == null) {
      result = IntValue.parse(text);
    }
    if (result == null) {
      text.setPosition(backup);
      return null;
    }
    return result;
  }
}
