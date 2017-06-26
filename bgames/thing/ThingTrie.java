package bgames.thing;

import bgames.trie.Trie;
import bgames.other.FieldPointer;
import bgames.field.Field;

public class ThingTrie {
  private final Trie<Thing> root;
  
  public ThingTrie(Trie<Thing> root) {
    this.root = root;
  }
  
  public Trie<Thing> getRoot() {
    return root;
  }
  
  public Field getField(FieldPointer pointer) {
    try {
      return root.get(pointer.getThingId()).getFields().get(pointer.getFieldId());
    }
    catch (NullPointerException exc) {
      return null;
    }
  }
}
