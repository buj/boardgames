package bgames.thing;

import bgames.trie.Trie;
import bgames.trie.Find;
import bgames.other.FieldPointer;
import bgames.field.Field;
import bgames.field.ValueField;
import bgames.field.ValueFieldAssign;
import bgames.value.Value;

public class ThingTrie {
  private final Trie<Thing> root;
  
  public ThingTrie(Trie<Thing> root) {
    this.root = root;
  }
  
  public Trie<Thing> getRoot() {
    return root;
  }
  public ThingTrie newRoot(Trie<Thing> root) {
    if (root == this.root) {
      return this;
    }
    return new ThingTrie(root);
  }
  
  public Field getField(FieldPointer pointer) {
    try {
      return root.get(pointer.getThingId()).getFields().get(pointer.getFieldId());
    }
    catch (NullPointerException exc) {
      return null;
    }
  }
  public Value getValue(FieldPointer pointer) {
    try {
      ValueField valueField = (ValueField)getField(pointer);
      return valueField.getValue();
    }
    catch (ClassCastException exc) {
      return null;
    }
  }
  public ThingTrie setValue(Value newValue, FieldPointer pointer) {
    Find<Thing> procedure = new Find<Thing>(pointer.getThingId(),
                            new Thing.GetFields(
                            new Find<Field>(pointer.getFieldId(),
                            new ValueFieldAssign(newValue))));
    return newRoot(procedure.apply(root));
  }
}
