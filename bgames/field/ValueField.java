package bgames.field;

import bgames.value.Value;
import bgames.other.ParseState;

public class ValueField extends Field {
  private final Value value;
  
  public ValueField(String id, Value value) {
    super(id);
    this.value = value;
  }
  
  public Value getValue() {
    return value;
  }
  public ValueField setValue(Value value) {
    return new ValueField(this.id, value);
  }
  
  public static ValueField parse(ParseState text) {
    int backup = text.getPosition();
    String id = text.readName();
    if (id == null) {
      return null;
    }
    if (!text.read("=")) {
      text.setPosition(backup);
      return null;
    }
    Value val = Value.parse(text);
    if (val == null) {
      text.setPosition(backup);
      return null;
    }
    return new ValueField(id, val);
  }
}
