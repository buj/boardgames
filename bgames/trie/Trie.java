package bgames.trie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class Trie<T> {
  private final T value;
  private final HashMap<Character, Trie<T>> table;
  
  public Trie(T value, HashMap<Character, Trie<T>> table) {
    this.value = value;
    this.table = table;
  }
  public Trie() {
    this(null, new HashMap<>());
  }
  
  public T getValue() {
    return value;
  }
  public boolean hasNext(Character ch) {
    return table.containsKey(ch);
  }
  public Trie<T> next(Character ch) {
    return table.get(ch);
  }
  public Iterator<Character> keyIterator() {
    return table.keySet().iterator();
  }
  
  public Trie<T> newValue(T value) {
    if (value == this.value) {
      return this;
    }
    return new Trie<T>(value, this.table);
  }
  public Trie<T> newChild(Character ch, Trie<T> child) {
    if (child == next(ch)) {
      return this;
    }
    HashMap<Character, Trie<T>> table = new HashMap<Character, Trie<T>>(this.table);
    table.put(ch, child);
    return new Trie<T>(this.value, table);
  }
  
  public static Stack<Character> strToStack(String str) {
    Stack<Character> stack = new Stack<>();
    for (int i = str.length() - 1; i >= 0; i--) {
      stack.push(str.charAt(i));
    }
    return stack;
  }
  private T get(Stack<Character> stack) {
    if (stack.empty()) {
      return value;
    }
    Character ch = stack.pop();
    if (!hasNext(ch)) {
      return null;
    }
    return next(ch).get(stack);
  }
  public T get(String name) {
    return get(strToStack(name));
  }
}
