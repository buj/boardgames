package bgames.field;

import bgames.value.Value;

public class ValueField implements Field {
  private final Value value;
  
  public ValueField(Value value) {
    this.value = value;
  }
  
  public Value getValue() {
    return value;
  }
  public ValueField setValue(Value value) {
    return new ValueField(value);
  }
}
