package bgames.stack;

import bgames.trie.Trie;
import bgames.trie.Find;
import bgames.trie.FindOrCreate;
import bgames.thing.Thing;
import bgames.World;
import bgames.value.Value;
import bgames.value.FieldPointer;
import bgames.field.Field;

public class Stack {
  private final Stack below;
  private final Trie<MemoryCell> memory;
  private final StackState state;
  private final String destination;
  
  private Stack(Stack below, Trie<MemoryCell> memory, StackState state, String destination) {
    this.below = below;
    this.memory = memory;
    this.state = state;
    this.destination = destination;
  }
  public Stack(StackState state, String destination) {
    this(null, new Trie<>(), state, destination);
  }
  public Stack(StackState state) {
    this(state, null);
  }
  
  public Field getField(String name, OutsideWorld outside) {
    Field result = null;
    try {
      result = memory.get(name).getField(outside);
    }
    catch (NullPointerException exc) {
      if (below != null) {
        result = below.getField(name, outside);
      }
    }
    return result;
  }
  public Value getValue(String name, OutsideWorld outside) {
    Value result = null;
    try {
      result = memory.get(name).getValue(outside);
    }
    catch (NullPointerException exc) {
      if (below != null) {
        result = below.getValue(name, outside);
      }
    }
    return result;
  }
  public FieldPointer getPointer(String name) {
    FieldPointer result = null;
    try {
      result = memory.get(name).getPointer();
    }
    catch (NullPointerException exc) {
      if (below != null) {
        result = below.getPointer(name);
      }
    }
    return result;
  }
  
  public Stack next(OutsideWorld outside) {
    return state.next(this, outside);
  }
  public boolean isReturnPoint() {
    return state.isReturnPoint();
  }
  
  public Stack setBelow(Stack below) {
    if (below == this.below) {
      return this;
    }
    return new Stack(below, this.memory, this.state, this.destination);
  }
  public Stack setMemory(Trie<MemoryCell> memory) {
    if (memory == this.memory) {
      return this;
    }
    return new Stack(this.below, memory, this.state, this.destination);
  }
  public Stack setState(StackState state) {
    if (state == this.state) {
      return this;
    }
    return new Stack(this.below, this.memory, state, this.destination);
  }
  
  private Stack setExistingNameValue(String name, Value value, OutsideWorld outside) {
    Find<MemoryCell> procedure = new Find<MemoryCell>(name,
                                 new MemoryCell.SetValue(value, outside));
    Stack result = setMemory(procedure.apply(memory));
    if (result == this) {
      if (below != null) {
        return setBelow(below.setExistingNameValue(name, value, outside));
      }
    }
    return result;
  }
  public Stack setNameValue(String name, Value value, OutsideWorld outside) {
    Stack result = setExistingNameValue(name, value, outside);
    if (result == this) {
      FindOrCreate<MemoryCell> procedure = new FindOrCreate<MemoryCell>(name,
                                           new MemoryCell.SetValue(value, outside));
      return setMemory(procedure.apply(memory));
    }
    return result;
  }
  
  public Stack push(Stack top) {
    return top.setBelow(this);
  }
  public Stack pop() {
    return below;
  }
  public Stack pop(Value returnValue, OutsideWorld outside) {
    return below.setNameValue(destination, returnValue, outside);
  }
}
