package bgames.field;

import bgames.value.Value;

public class ValueField extends Field {
  private final Value value;
  
  public ValueField(Field nested, int priority, boolean isTemp, Value value) {
    super(nested, priority, isTemp);
    this.value = value;
  }
  
  public Value getValue() {
    return value;
  }
  public ValueField setValue(Value value) {
    return new ValueField(this.nested, this.priority, this.isTemp, value);
  }
}
