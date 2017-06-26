package bgames.trie;

import java.util.function.Function;

public class Assign<T> implements Function<Trie<T>, Trie<T>> {
  private final T value;
  
  public Assign(T value) {
    this.value = value;
  }
  
  @Override
  public Trie<T> apply(Trie<T> trie) {
    return trie.setValue(value);
  }
}
