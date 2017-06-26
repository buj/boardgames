package bgames.other;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NameList implements Iterable<String> {
  private final String[] names;
  
  public NameList(String[] names) {
    this.names = names;
  }
  
  public int getLength() {
    return names.length;
  }
  
  public class NameIterator implements Iterator<String> {
    private int position;
    private final NameList source;
    
    public NameIterator(NameList source) {
      this.position = 0;
      this.source = source;
    }
    
    @Override
    public boolean hasNext() {
      return position < source.names.length;
    }
    @Override
    public String next() {
      if (!hasNext()) {
        throw new NoSuchElementException("End of name list.");
      }
      position += 1;
      return source.names[position - 1];
    }
  }
  @Override
  public NameIterator iterator() {
    return new NameIterator(this);
  }
}
