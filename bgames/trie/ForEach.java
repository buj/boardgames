package bgames.trie;

import java.util.function.Function;
import java.util.Iterator;

public class ForEach<T> implements Function<Trie<T>, Trie<T>> {
  private final Function<T, T> nextFunction;
  
  public ForEach(Function<T, T> nextFunction) {
    this.nextFunction = nextFunction;
  }
  public ForEach() {
    this(Function.identity());
  }
  
  @Override
  public Trie<T> apply(Trie<T> trie) {
    Iterator<Character> it = trie.keyIterator();
    Trie<T> res = trie.newValue(nextFunction.apply(trie.getValue()));
    while (it.hasNext()) {
      Character ch = it.next();
      res = res.newChild(ch, apply(trie.next(ch)));
    }
    return res;
  }
}
