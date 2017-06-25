package bgames.value;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Location implements Value, Iterable<String> {
  private final String[] names;
  
  public Location(String[] names) {
    this.names = names;
  }
  public class LocationIterator implements Iterator<String> {
    private int position;
    private final Location source;
    
    public LocationIterator(Location source) {
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
        throw new NoSuchElementException("End of location.");
      }
      position += 1;
      return source.names[position - 1];
    }
  }
  public LocationIterator iterator() {
    return new LocationIterator(this);
  }
}
