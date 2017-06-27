package bgames.value;

import bgames.trie.Trie;
import bgames.trie.FindOrCreate;
import bgames.trie.Assign;

public class ValueList implements Value{
  private final Trie<Value> trie;
  private final int start, end;
  
  private ValueList(Trie<Value> trie, int start, int end) {
    this.trie = trie;
    this.start = start;
    this.end = end;
  }
  public ValueList(Value[] values) {
    Trie<Value> tempTrie = new Trie<>();
    for (int i = 0; i < values.length; i++) {
      FindOrCreate<Value> procedure = new FindOrCreate<Value>(String.valueOf(i),
                                      new Assign<Value>(values[i]));
      tempTrie = procedure.apply(tempTrie);
    }
    trie = tempTrie;
    start = 0;
    end = values.length;
  }
  
  public int getLength() {
    return end - start;
  }
  public boolean inRange(int i) {
    return (i >= 0) && (i < getLength());
  }
  public Value get(int i) {
    return trie.get(String.valueOf(start + i));
  }
  
  public ValueList set(int i, Value value) {
    if (inRange(i)) {
      FindOrCreate<Value> procedure = new FindOrCreate<Value>(String.valueOf(start + i),
                                      new Assign<Value>(value));
      return new ValueList(procedure.apply(trie), start, end);
    }
    return this;
  }
}
