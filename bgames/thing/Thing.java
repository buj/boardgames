package bgames.thing;

import java.util.function.Function;

import bgames.trie.Trie;
import bgames.trie.FindOrCreate;
import bgames.trie.Assign;
import bgames.field.Field;
import bgames.other.ParseState;

public class Thing {
  private final String id;
  private final Trie<Field> fields;
  
  public Thing(String id, Trie<Field> fields) {
    this.id = id;
    this.fields = fields;
  }
  
  public String getId() {
    return id;
  }
  public Trie<Field> getFields() {
    return fields;
  }
  public Thing setFields(Trie<Field> fields) {
    if (fields == this.fields) {
      return this;
    }
    return new Thing(this.id, fields);
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
  

  public static Thing parse(ParseState text) {
    int backup = text.getPosition();
    String id = text.readName();
    if (id == null) {
      return null;
    }
    if (!text.read("{")) {
      text.setPosition(backup);
      return null;
    }
    Trie<Field> result = new Trie<>();
    while (true) {
      if (text.read("}")) {
        break;
      }
      Field field = Field.parse(text);
      if (field == null) {
        text.setPosition(backup);
        return null;
      }
      FindOrCreate<Field> procedure = new FindOrCreate<Field>(field.getId(),
                                      new Assign<Field>(field));
      result = procedure.apply(result);
    }
    return new Thing(id, result);
  }
}
