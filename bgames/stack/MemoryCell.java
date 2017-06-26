package bgames.stack;

import java.util.function.Function;

import bgames.value.Value;
import bgames.other.FieldPointer;

public class MemoryCell {
  private final FieldPointer pointer;
  private final Value value;
  
  public MemoryCell(Value value) {
    this.pointer = null;
    this.value = value;
  }
  public MemoryCell(FieldPointer pointer) {
    this.pointer = pointer;
    this.value = null;
  }
  
  public boolean isPointer() {
    return pointer != null;
  }
  public boolean isValue() {
    return value != null;
  }
  
  public Value getValue(OutsideWorld outside) {
    if (isPointer()) {
      return outside.getThings().getValue(pointer);
    }
    return value;
  }
  public MemoryCell setValue(Value value, OutsideWorld outside) {
    if (isPointer()) {
      outside.setThings(outside.getThings().setValue(value, pointer));
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
      return cell.setValue(value, outside);
    }
  }
}
