package bgames.field;

import java.util.function.Function;

import bgames.value.Value;

public class ValueFieldAssign implements Function<Field, Field> {
  private final Value value;
  
  public ValueFieldAssign(Value value) {
    this.value = value;
  }
  
  @Override
  public Field apply(Field field) {
    ValueField valueField = (ValueField)field;
    return valueField.setValue(value);
  }
}
