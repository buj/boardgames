package bgames.stack;

import java.util.function.Function;

import bgames.value.Value;
import bgames.value.FieldPointer;
import bgames.field.Field;

public class MemoryCell {
  private final Value value;
  
  public MemoryCell(Value value) {
    this.value = value;
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
    return new MemoryCell(value);
  }
  
  public static class SetValue implements Function<MemoryCell, MemoryCell> {
    private final Value value;
    private final OutsideWorld outside;
    
    public SetValue(Value value, OutsideWorld outside) {
      this.value = value;
      this.outside = outside;
    }
    
    @Override
    public MemoryCell apply(MemoryCell cell) {
      if (cell != null) {
        return cell.setValue(value, outside);
      }
      return new MemoryCell(value);
    }
  }
}
