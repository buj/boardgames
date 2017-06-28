package bgames.stack;

import java.util.function.Function;

import bgames.value.Value;
import bgames.value.FieldPointer;
import bgames.field.Field;

public class MemoryCell {
  private final String id;
  private final Value value;
  
  public MemoryCell(String id, Value value) {
    this.id = id;
    this.value = value;
  }
  
  public String getId() {
    return id;
  }
  public boolean isPointer() {
    return (value instanceof FieldPointer);
  }
  
  public FieldPointer getPointer() {
    if (isPointer()) {
      return (FieldPointer)value;
    }
    return null;
  }
  public Field getField(OutsideWorld outside) {
    if (isPointer()) {
      return outside.getThings().getField((FieldPointer)value);
    }
    return null;
  }
  public Value getValue(OutsideWorld outside) {
    if (isPointer()) {
      return outside.getThings().getValue((FieldPointer)value);
    }
    return value;
  }
  public MemoryCell setValue(Value value, OutsideWorld outside) {
    if (isPointer()) {
      outside.setThings(outside.getThings().setValue(value, (FieldPointer)this.value));
      return this;
    }
    return new MemoryCell(this.id, value);
  }
  
  public static class SetValue implements Function<MemoryCell, MemoryCell> {
    private final String id;
    private final Value value;
    private final OutsideWorld outside;
    
    public SetValue(String id, Value value, OutsideWorld outside) {
      this.id = id;
      this.value = value;
      this.outside = outside;
    }
    
    @Override
    public MemoryCell apply(MemoryCell cell) {
      if (cell != null) {
        return cell.setValue(value, outside);
      }
      return new MemoryCell(id, value);
    }
  }
  
  @Override
  public String toString() {
    return id + " = " + value.toString();
  }
}
