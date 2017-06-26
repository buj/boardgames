package bgames.value;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ValueList implements Value, Iterable<Value> {
  private final Value[] values;
  
  public ValueList(Value[] values) {
    this.values = values;
  }
  
  public int getLength() {
    return values.length;
  }
  public Value get(int i) {
    try {
      return values[i];
    }
    catch (IndexOutOfBoundsException exc) {
      return null;
    }
  }
  public ValueList set(int i, Value value) {
    try {
      Value[] newValues = Arrays.copyOf(values, values.length);
      newValues[i] = value;
      return new ValueList(newValues);
    }
    catch (IndexOutOfBoundsException exc) {
      return this;
    }
  }
  
  public class ValueIterator implements Iterator<Value> {
    private int position;
    private final ValueList source;
    
    public ValueIterator(ValueList source) {
      this.position = 0;
      this.source = source;
    }
    
    @Override
    public boolean hasNext() {
      return position < source.values.length;
    }
    @Override
    public Value next() {
      if (!hasNext()) {
        throw new NoSuchElementException("End of value list.");
      }
      position += 1;
      return source.values[position - 1];
    }
  }
  @Override
  public ValueIterator iterator() {
    return new ValueIterator(this);
  }
}
