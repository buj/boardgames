package bgames.trie;

import java.util.function.Function;
import java.util.Stack;

public class FindOrCreate<T> extends Find<T> {
  
  public FindOrCreate(String name, Function<T, T> nextFunction) {
    super(name, nextFunction);
  }
  public FindOrCreate(String name) {
    super(name);
  }
  
  @Override
  protected Trie<T> apply(Trie<T> trie, Stack<Character> stack) {
    if (stack.empty()) {
      return trie.setValue(nextFunction.apply(trie.getValue()));
    }
    Character ch = stack.pop();
    Trie<T> nextTrie = trie.next(ch);
    if (nextTrie == null) {
      nextTrie = new Trie<>();
    }
    return trie.setChild(ch, apply(nextTrie, stack));
  }
}
