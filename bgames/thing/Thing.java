package bgames.thing;

import bgames.trie.Trie;
import bgames.field.Field;

public class Thing {
  private final Trie<Field> fields;
  
  public Thing(Trie<Field> fields) {
    this.fields = fields;
  }
  
  public Trie<Field> getFields() {
    return fields;
  }
}
