package bgames.thing;

import java.util.function.Function;

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
  public Thing setFields(Trie<Field> fields) {
    if (fields == this.fields) {
      return this;
    }
    return new Thing(fields);
  }
  
  public static class GetFields implements Function<Thing, Thing> {
    private final Function<Trie<Field>, Trie<Field>> nextFunction;
    
    public GetFields(Function<Trie<Field>, Trie<Field>> nextFunction) {
      this.nextFunction = nextFunction;
    }
    
    @Override
    public Thing apply(Thing thing) {
      return thing.setFields(nextFunction.apply(thing.fields));
    }
  }
}
