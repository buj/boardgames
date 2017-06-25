package bgames.trie;

import java.util.function.Function;
import java.util.Stack;

public class Find<T> {
  private final Function<T, T> nextFunction;
  
  public Find(Function<T, T> nextFunction) {
    this.nextFunction = nextFunction;
  }
  public Find() {
    this(Function.identity());
  }
  
  private Trie<T> apply(Trie<T> trie, Stack<Character> stack) {
    if (stack.empty()) {
      return trie.newValue(nextFunction.apply(trie.getValue()));
    }
    Character ch = stack.pop();
    if (!trie.hasNext(ch)) {
      return trie;
    }
    return trie.newChild(ch, apply(trie.next(ch), stack));
  }
  public Trie<T> apply(Trie<T> trie, String name) {
    return apply(trie, Trie.strToStack(name));
  }
}
