package bgames.stack;

import bgames.trie.Trie;
import bgames.trie.FindOrCreate;
import bgames.thing.Thing;
import bgames.World;
import bgames.value.Value;

public class Stack {
  private final Stack below;
  private final Trie<MemoryCell> memory;
  private final StackState state;
  private final String destination;
  
  public Stack(Stack below, Trie<MemoryCell> memory, StackState state, String destination) {
    this.below = below;
    this.memory = memory;
    this.state = state;
    this.destination = destination;
  }
  public Stack(StackState state, String destination) {
    this(null, new Trie<>(), state, destination);
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
  
  public Stack next(OutsideWorld outside) {
    return state.next(this, outside);
  }
  public boolean isReturnPoint() {
    return state.isReturnPoint();
  }
  
  public Stack setBelow(Stack below) {
    return new Stack(below, this.memory, this.state, this.destination);
  }
  public Stack setMemory(Trie<MemoryCell> memory) {
    return new Stack(this.below, memory, this.state, this.destination);
  }
  public Stack setState(StackState state) {
    return new Stack(this.below, this.memory, state, this.destination);
  }
  
  public Stack setNameValue(String name, Value value, OutsideWorld outside) {
    FindOrCreate<MemoryCell> procedure = new FindOrCreate<MemoryCell>(name,
                                         new MemoryCell.SetValue(value, outside));
    return setMemory(procedure.apply(memory));
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
