package bgames.trie;

import java.util.function.Function;

public class Assign<T> implements Function<T, T> {
  private final T value;
  
  public Assign(T value) {
    this.value = value;
  }
  
  @Override
  public T apply(T oldValue) {
    return value;
  }
}
