package bgames.thing;

import java.util.ArrayList;

import bgames.trie.Trie;
import bgames.trie.Find;
import bgames.value.FieldPointer;
import bgames.field.Field;
import bgames.field.ValueField;
import bgames.field.ValueFieldAssign;
import bgames.value.Value;
import bgames.value.ThingPointer;

public class ThingTrie {
  private final Trie<Thing> root;
  
  public ThingTrie(Trie<Thing> root) {
    this.root = root;
  }
  
  public int getMaxId() {
    ArrayList<String> names = root.collect();
    int maxId = -1;
    for (int i = 0; i < names.size(); i++) {
      try {
        int id = Integer.parseInt(names.get(i));
        if (id > maxId) {
          maxId = id;
        }
      }
      catch (NumberFormatException exc) {}
    }
    return maxId;
  }
  
  public Trie<Thing> getRoot() {
    return root;
  }
  public ThingTrie setRoot(Trie<Thing> root) {
    if (root == this.root) {
      return this;
    }
    return new ThingTrie(root);
  }
  
  public Thing getThing(ThingPointer pointer) {
    return root.get(pointer.getId());
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
    return setRoot(procedure.apply(root));
  }
}
