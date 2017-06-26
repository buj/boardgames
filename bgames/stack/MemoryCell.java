package bgames.stack;

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
  public Value getValue(Stack.OutsideWorld outside) {
    if (isPointer()) {
      
    }
    return value;
  }
}
