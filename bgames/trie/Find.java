package bgames.trie;

import java.util.function.Function;
import java.util.Stack;

public class Find<T> implements Function<Trie<T>, Trie<T>> {
  protected final String name;
  protected final Function<T, T> nextFunction;
  
  public Find(String name, Function<T, T> nextFunction) {
    this.name = name;
    this.nextFunction = nextFunction;
  }
  public Find(String name) {
    this(name, Function.identity());
  }
  
  protected Trie<T> apply(Trie<T> trie, Stack<Character> stack) {
    if (stack.empty()) {
      return trie.setValue(nextFunction.apply(trie.getValue()));
    }
    Character ch = stack.pop();
    if (!trie.hasNext(ch)) {
      return trie;
    }
    return trie.setChild(ch, apply(trie.next(ch), stack));
  }
  @Override
  public Trie<T> apply(Trie<T> trie) {
    return apply(trie, Trie.strToStack(name));
  }
}
